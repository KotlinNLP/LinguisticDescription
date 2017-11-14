/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import com.beust.klaxon.string
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.progressindicator.ProgressIndicatorBar
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream

/**
 * The dictionary of morphologies that maps forms to morphologies.
 */
class MorphologyDictionary {

  /**
   * A data entry of the morphology map.
   */
  data class Entry(
    val form: String,
    val multipleForm: List<String>?,
    val morphologies: MutableList<Morphology>
  )

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

      val file = File(filename)
      val dictionary = MorphologyDictionary()
      val progress = ProgressIndicatorBar(file.getNumOfLines())

      file.forEachLine { line ->

        if (verbose) progress.tick()

        val entryObj = Parser().parse(line.toInputStream()) as JsonObject

        dictionary.addEntry(forms = getForms(entryObj), morphologies = getMorphologies(entryObj))
      }

      return dictionary
    }

    /**
     *
     */
    private fun getForms(entryObj: JsonObject): List<String> = try {

      entryObj.array<String>("form")!!.toList()

    } catch (e: ClassCastException) {

      listOf(entryObj.string("form")!!)
    }

    /**
     *
     */
    private fun getMorphologies(entryObj: JsonObject): List<Morphology> {
      TODO("not implemented")
    }

    /**
     * @return this [String] converted to an [InputStream]
     */
    private fun String.toInputStream(): InputStream = ByteArrayInputStream(this.toByteArray())

    /**
     * @return the number of lines of this file
     */
    private fun File.getNumOfLines(): Int {

      var count = 0

      this.reader().forEachLine { count++ }

      return count
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
  private val morphologyMap = mutableMapOf<String, Entry>()

  /**
   * Add a new entry to the morphology map or add new [morphologies] to it if already present.
   *
   * @param forms the list of forms of the entry
   * @param morphologies the list of morphologies of the entry
   */
  private fun addEntry(forms: List<String>, morphologies: List<Morphology>) {

    val uniqueForm: String = forms.joinToString(" ")

    if (uniqueForm !in this.morphologyMap) {

      this.morphologyMap[uniqueForm] = Entry(
        form = uniqueForm,
        multipleForm = if (forms.size > 1) forms else null,
        morphologies = morphologies.toMutableList())

    } else {

      this.addMorphologies(form = uniqueForm, morphologies = morphologies)
    }
  }

  /**
   * Add the given [morphologies] to the entry with the given [form].
   *
   * @param form a form
   * @param morphologies the list of morphologies to add
   */
  private fun addMorphologies(form: String, morphologies: List<Morphology>) {

    this.morphologyMap[form]!!.morphologies.addAll(morphologies)
  }
}
