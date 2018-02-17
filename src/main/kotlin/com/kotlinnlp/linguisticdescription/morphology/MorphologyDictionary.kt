/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.morphologies.MorphologyFactory
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyPropertyFactory
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyType
import com.kotlinnlp.linguisticdescription.utils.forEachLine
import com.kotlinnlp.linguisticdescription.utils.getNumOfLines
import com.kotlinnlp.linguisticdescription.utils.toInputStream
import com.kotlinnlp.progressindicator.ProgressIndicatorBar

/**
 * The dictionary of morphologies that maps forms to morphologies.
 */
class MorphologyDictionary {

  /**
   * The [MorphologyEntry] type.
   */
  enum class MorphologyEntryType { Single, Multiple }

  /**
   * The morphology entry.
   * If [type] is [MorphologyEntryType.Single] the list contains only one morphology, otherwise more.
   */
  data class MorphologyEntry(
    val type: MorphologyEntryType,
    val list: List<Morphology>
  ) {

    /**
     * Build a [MorphologyEntry] given a list of [Morphology].
     *
     * @param morphologies the list of morphologies
     */
    constructor(morphologies: List<Morphology>): this(
      type = if (morphologies.size == 1) MorphologyEntryType.Single else MorphologyEntryType.Multiple,
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
  private inner class RowEntry(
    val form: String,
    val multipleForm: List<String>?,
    var morphologies: MutableList<List<String>>
  ) {

    /**
     * Convert this [RowEntry] to an [Entry].
     *
     * @return the entry interpreted from this row entry
     */
    fun toEntry(): Entry = Entry(
      form = this.form,
      multipleForm = this.multipleForm,
      morphologies = this.morphologies.map { MorphologyEntry(it.toMorphologies()) }
    )

    /**
     * Convert this list of JSON morphologies to a list of [Morphology] objects.
     *
     * @throws InvalidMorphologyType when an object of this list contains an invalid type annotation
     *
     * @return a list of [Morphology] objects
     */
    private fun List<String>.toMorphologies(): List<Morphology> = this.map { jsonMorphology ->

      val morphoObj = Parser().parse(jsonMorphology) as JsonObject
      val typeAnnotation: String = morphoObj.string("type")!!

      if (typeAnnotation !in this@MorphologyDictionary.annotationsMap) throw InvalidMorphologyType(typeAnnotation)

      MorphologyFactory(
        lemma = morphoObj.string("lemma")!!,
        type = this@MorphologyDictionary.annotationsMap[typeAnnotation]!!,
        properties = morphoObj.obj("properties")!!
          .filter { it.value != null }
          .mapValues { MorphologyPropertyFactory(propertyType = it.key, valueAnnotation = it.value as String) }
      )
    }
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
          jsonMorphologies = entryObj.array<JsonObject>("morpho")!!.map { it.toJsonString() })

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
   * The map of morphology types associated by annotation.
   */
  private val annotationsMap: Map<String, MorphologyType> = MorphologyType.values().associateBy { it.annotation }

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
   * Add a new entry to the morphology map or add new [jsonMorphologies] to it if already present.
   *
   * @param forms the list of forms of the entry
   * @param jsonMorphologies the JSON morphologies array of the entry
   */
  private fun addEntry(forms: List<String>, jsonMorphologies: List<String>) {

    val uniqueForm: String = forms.joinToString(separator = " ")

    if (uniqueForm !in this.morphologyMap) {

      this.morphologyMap[uniqueForm] = RowEntry(
        form = uniqueForm,
        multipleForm = if (forms.size > 1) forms else null,
        morphologies = mutableListOf(jsonMorphologies)
      )

    } else {
      this.addMorphologies(form = uniqueForm, jsonMorphologies = jsonMorphologies)
    }
  }

  /**
   * Add the given [jsonMorphologies] to the entry with the given [form].
   *
   * @param form a form
   * @param jsonMorphologies the morphology entry to add
   */
  private fun addMorphologies(form: String, jsonMorphologies: List<String>) =
    this.morphologyMap[form]!!.morphologies.add(jsonMorphologies)
}
