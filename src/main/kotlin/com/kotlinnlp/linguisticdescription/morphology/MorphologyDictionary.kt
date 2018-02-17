/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.utils.forEachLine
import com.kotlinnlp.linguisticdescription.utils.getNumOfLines
import com.kotlinnlp.linguisticdescription.utils.toInputStream
import com.kotlinnlp.progressindicator.ProgressIndicatorBar

/**
 * The dictionary of morphologies that maps forms to morphologies.
 */
class MorphologyDictionary {

  /**
   * The morphology entry.
   * If [type] is [Type.Single] the list contains only one morphology, otherwise more.
   */
  data class MorphologyEntry(
    val type: Type,
    val list: List<Morphology>
  ) {

    /**
     * The [MorphologyEntry] type.
     */
    enum class Type { Single, Multiple }

    /**
     * Build a [MorphologyEntry] given a list of [Morphology].
     *
     * @param morphologies the list of morphologies
     */
    constructor(morphologies: List<Morphology>): this(
      type = if (morphologies.size == 1) Type.Single else Type.Multiple,
      list = morphologies
    )
  }

  /**
   * A data entry of the morphology map.
   */
  data class Entry(
    val form: String,
    val multipleForm: List<String>?,
    val morphologies: List<MorphologyEntry>)

  /**
   * A data entry of the morphology map, with the morphologies list not interpreted.
   */
  private data class RowEntry(
    val form: String,
    val multipleForm: List<String>?,
    var morphologies: MutableList<List<MorphologyCompressor.EncodedMorphology>>
  ) {

    /**
     * Convert this [RowEntry] to an [Entry].
     *
     * @return the entry interpreted from this row entry
     */
    fun toEntry(): Entry = Entry(
      form = this.form,
      multipleForm = this.multipleForm,
      morphologies = this.morphologies.map { encodedMorphologies ->
        MorphologyEntry(morphologies = encodedMorphologies.map { it.decode() })
      }
    )
  }

  companion object {

    /**
     * Load a [MorphologyDictionary] from the JSONL file with the given [filename].
     *
     * @param filename the morphologies dictionary filename
     * @param verbose whether to print the reading progress
     *
     * @return a new Morphology Dictionary
     */
    fun load(filename: String, verbose: Boolean = true): MorphologyDictionary {

      val dictionary = MorphologyDictionary()

      val jsonParser = Parser()
      val progress = ProgressIndicatorBar(getNumOfLines(filename))

      forEachLine(filename) { line ->

        val entryObj = jsonParser.parse(line.toInputStream()) as JsonObject

        dictionary.addEntry(
          forms = getForms(entryObj),
          encodedMorphologies = entryObj.array<JsonObject>("morpho")!!.map {
            dictionary.compressor.encodeMorphology(it)
          })

        if (verbose) progress.tick()
      }

      return dictionary
    }

    /**
     * @param entryObj the JsonObject of a input file entry
     *
     * @return the list of forms of the given entry
     */
    private fun getForms(entryObj: JsonObject): List<String> = try {

      entryObj.array<String>("form")!!.toList()

    } catch (e: ClassCastException) {

      listOf(entryObj.string("form")!!)
    }
  }

  /**
   * The size of the dictionary (number of entries).
   */
  val size: Int get() = this.morphologyMap.size

  /**
   * The compressor of morphologies.
   */
  private val compressor = MorphologyCompressor()

  /**
   * The map of forms to [Entry] objects.
   */
  private val morphologyMap = mutableMapOf<String, MorphologyDictionary.RowEntry>()

  /**
   * @param form a form of the dictionary
   *
   * @return the [Entry] related to the given [form] if present, otherwise null
   */
  operator fun get(form: String): MorphologyDictionary.Entry? = this.morphologyMap[form]?.toEntry()
  /**
   * Add a new entry to the morphology map or add new [encodedMorphologies] to it if already present.
   *
   * @param forms the list of forms of the entry
   * @param encodedMorphologies the encoded morphologies of the entry, given from the [compressor]
   */
  private fun addEntry(forms: List<String>, encodedMorphologies: List<MorphologyCompressor.EncodedMorphology>) {

    val uniqueForm: String = forms.joinToString(separator = " ")

    if (uniqueForm !in this.morphologyMap) {

      this.morphologyMap[uniqueForm] = RowEntry(
        form = uniqueForm,
        multipleForm = if (forms.size > 1) forms else null,
        morphologies = mutableListOf(encodedMorphologies)
      )

    } else {
      this.addMorphologies(form = uniqueForm, indices = encodedMorphologies)
    }
  }

  /**
   * Add the given [indices] to the [RowEntry] with the given [form].
   *
   * @param form a form
   * @param indices the encoded morphologies to add to the given form
   */
  private fun addMorphologies(form: String, indices: List<MorphologyCompressor.EncodedMorphology>) =
    this.morphologyMap[form]!!.morphologies.add(indices)
}
