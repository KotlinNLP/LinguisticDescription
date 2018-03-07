/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.dictionary

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.string
import com.kotlinnlp.progressindicator.ProgressIndicatorBar

/**
 * The helper that explodes the accentuated forms of a [MorphologyDictionary].
 *
 * @param languageCode the iso-a2 code of the language whose accents regex list must be used
 * @param verbose whether to print the exploding progress (default = true)
 */
class AccentsHelper(languageCode: String, private val verbose: Boolean = true) {

  companion object {

    /**
     * A map of lists of pairs <regex, replacement> for the accents, associated by language iso-a2 code.
     */
    private val accentsRegexMap: Map<String, List<Pair<Regex, String>>> = this.loadAccentsRegexList()

    /**
     * Load the list of regular expression for the accents with their replacements from the resources.
     *
     * @return a map of lists of pairs <regex, replacement> associated by language iso-a2 code
     */
    private fun loadAccentsRegexList(): Map<String, List<Pair<Regex, String>>> {

      val filename: String = MorphologyDictionary::class.java.classLoader.getResource("accents_regex.json").file
      val regexObj: JsonObject = Parser().parse(filename) as JsonObject

      return regexObj.mapValues { (_, regexList) -> regexList as JsonArray<*>
        regexList.map { obj -> obj as JsonObject
          Pair(Regex(obj.string("pattern")!!), obj.string("replacement")!!)
        }
      }
    }
  }

  /**
   * A list of pairs <regex, replacement> for the accents.
   */
  private val accentsRegexList: List<Pair<Regex, String>> = accentsRegexMap.getOrElse(languageCode.toLowerCase()) {
    throw RuntimeException("Language not supported: $languageCode.")
  }

  /**
   * Explode the keys of a [morphologyMap] by accents, adding new missing entries by reference.
   *
   * @param morphologyMap the morphology map of a [MorphologyDictionary]
   */
  fun explodeByAccents(morphologyMap: MutableMap<String, String>) {

    val progress = ProgressIndicatorBar(morphologyMap.size)

    for (form in morphologyMap.keys.toList()) { // avoid concurrent modifications calling .toList()

      explodeGroupByAccents(formsGroup = form.split(' ')) // split in case of multi-words
        .filter { it.isNotEmpty() }
        .forEach { alternativeFormsGroup ->
          val altForm: String = alternativeFormsGroup.joinToString(separator = " ")
          morphologyMap.putIfAbsent(altForm, MorphologyDictionary.REF_PREFIX + form)
        }

      if (this.verbose) progress.tick()
    }
  }

  /**
   * Explode a forms group by accents recursively for each form in the group.
   *
   * @param formsGroup a group of forms (size > 1 if multi-words)
   * @param formIndex the index, within the [formsGroup], of the form to explode (initially 0)
   * @param explodedGroups the list of forms groups already exploded (initially empty)
   *
   * @return the list of forms groups exploded starting from the form of the [formsGroup] at the given [formIndex]
   */
  private fun explodeGroupByAccents(formsGroup: List<String>,
                                    formIndex: Int = 0,
                                    explodedGroups: List<List<String>> = listOf()): List<List<String>> {

    val explodedForms: List<String> = explodeFormByAccents(form = formsGroup[formIndex])

    val explodingGroups: List<List<String>> = if (explodedGroups.isNotEmpty()) explodedGroups else listOf(formsGroup)
    val newGroups: List<List<String>> = explodingGroups.flatMap {
      this.explodeReplacing(formsGroup = it, formIndex = formIndex, replacements = explodedForms)
    }

    return (explodedGroups + newGroups).let {
      if (formIndex < formsGroup.lastIndex)
        it + this.explodeGroupByAccents(formsGroup = formsGroup, formIndex = formIndex + 1, explodedGroups = it)
      else
        it
    }
  }

  /**
   * Explode a form by accents.
   *
   * @param form a form
   *
   * @return the list of exploded forms (empty if no regex matched)
   */
  private fun explodeFormByAccents(form: String): List<String> {

    val explodedForms = mutableListOf<String>()

    this.accentsRegexList.forEach { (regex, replacement) ->
      if (regex.containsMatchIn(form)) {
        val newForm: String = regex.replace(input = form, replacement = replacement)
        explodedForms.add(newForm)
      }
    }

    return explodedForms
  }

  /**
   * Replace each replacement of [replacements] to the form at the given [formIndex] in the [formsGroup], obtaining new
   * forms groups.
   *
   * @param formsGroup a group of forms
   * @param formIndex the index of the form to replace, within the [formsGroup]
   * @param replacements the list of replacements
   *
   * @return a list of forms groups
   */
  private fun explodeReplacing(formsGroup: List<String>,
                               formIndex: Int,
                               replacements: List<String>): List<List<String>> {

    return replacements.map { replacement ->
      formsGroup.subList(0, formIndex) + replacement + formsGroup.subList(formIndex + 1, formsGroup.size)
    }
  }
}
