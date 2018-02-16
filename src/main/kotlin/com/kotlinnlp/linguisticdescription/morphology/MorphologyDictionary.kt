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
    val list: List<Morphology>)

  /**
   * A data entry of the morphology map.
   */
  data class Entry(
    val form: String,
    val multipleForm: List<String>?,
    val morphologies: MutableList<MorphologyEntry>)

  companion object {

    /**
     * The map of morphology types associated by annotation.
     */
    private val annotationsMap: Map<String, MorphologyType> = MorphologyType.values().associateBy { it.annotation }

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

        dictionary.addEntry(forms = getForms(entryObj), morphologies = getMorphologies(entryObj))

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

    /**
     * @param entryObj the JsonObject of a input file entry
     *
     * @throws InvalidMorphologyType when the [entryObj] contains an invalid type annotation
     *
     * @return the list of morphologies of the given entry
     */
    private fun getMorphologies(entryObj: JsonObject): List<Morphology> {

      return entryObj.array<JsonObject>("morpho")!!.map { morphoObj ->

        val typeAnnotation: String = morphoObj.string("type")!!

        if (typeAnnotation !in this.annotationsMap) throw InvalidMorphologyType(typeAnnotation)

        MorphologyFactory(
          lemma = morphoObj.string("lemma")!!,
          type = this.annotationsMap[typeAnnotation]!!,
          properties = morphoObj.obj("properties")!!
            .filter { it.value != null }
            .mapValues { MorphologyPropertyFactory(propertyType = it.key, valueAnnotation = it.value as String) }
        )
      }
    }
  }

  /**
   * The size of the dictionary (number of entries).
   */
  val size: Int get() = this.morphologyMap.size

  /**
   * @param form a form of the dictionary
   *
   * @return the [Entry] related to the given [form] if present, otherwise null
   */
  operator fun get(form: String): MorphologyDictionary.Entry? = this.morphologyMap[form]

  /**
   * The map of forms to [Entry] objects.
   */
  private val morphologyMap = mutableMapOf<String, MorphologyDictionary.Entry>()

  /**
   * Add a new entry to the morphology map or add new [morphologies] to it if already present.
   *
   * @param forms the list of forms of the entry
   * @param morphologies the list of morphologies of the entry
   */
  private fun addEntry(forms: List<String>, morphologies: List<Morphology>) {

    val uniqueForm: String = forms.joinToString(separator = " ")

    if (uniqueForm !in this.morphologyMap) {

      this.morphologyMap[uniqueForm] = Entry(
        form = uniqueForm,
        multipleForm = if (forms.size > 1) forms else null,
        morphologies = mutableListOf(this.buildMorphologyEntry(morphologies))
      )

    } else {
      this.addMorphology(form = uniqueForm, morphologyEntry = this.buildMorphologyEntry(morphologies))
    }
  }

  /**
   * Build a [MorphologyEntry] given a list of [Morphology].
   *
   * @param morphologies the list of morphologies
   *
   * @return a new morphology entry
   */
  private fun buildMorphologyEntry(morphologies: List<Morphology>) = MorphologyEntry(
    type = if (morphologies.size == 1) MorphologyEntryType.Single else MorphologyEntryType.Multiple,
    list = morphologies
  )

  /**
   * Add the given [morphologyEntry] to the entry with the given [form].
   *
   * @param form a form
   * @param morphologyEntry the morphology entry to add
   */
  private fun addMorphology(form: String, morphologyEntry: MorphologyEntry) {

    this.morphologyMap[form]!!.morphologies.add(morphologyEntry)
  }
}
