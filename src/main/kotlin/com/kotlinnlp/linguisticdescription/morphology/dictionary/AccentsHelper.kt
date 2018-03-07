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
 */
object AccentsHelper {

  /**
   * A list of pairs <regex, replacement> for the accents.
   */
  private val accentsRegexList: List<Pair<Regex, String>> = this.loadAccentsRegex()

  /**
   * Explode the keys of a [morphologyMap] by accents, adding new missing entries by reference.
   *
   * @param morphologyMap the morphology map of a [MorphologyDictionary]
   * @param verbose whether to print the exploding progress
   */
  fun explodeByAccents(morphologyMap: MutableMap<String, String>, verbose: Boolean) {

    val progress = ProgressIndicatorBar(morphologyMap.size)

    for (form in morphologyMap.keys.toList()) { // avoid concurrent modifications calling .toList()

      explodeGroupByAccents(formsGroup = form.split(' ')) // split in case of multi-words
        .filter { it.isNotEmpty() }
        .forEach { alternativeFormsGroup ->
          val altForm: String = alternativeFormsGroup.joinToString(separator = " ")
          morphologyMap.putIfAbsent(altForm, MorphologyDictionary.REF_PREFIX + form)
        }

      if (verbose) progress.tick()
    }
  }

  /**
   * Load the list of regular expression for the accents with their replacements from the resources.
   *
   * @return a list of pairs <regex, replacement>
   */
  private fun loadAccentsRegex(): List<Pair<Regex, String>> {

    val filename: String = MorphologyDictionary::class.java.classLoader.getResource("accents_regex.json").file
    val regexList: JsonArray<*> = Parser().parse(filename) as JsonArray<*>

    return regexList.map { obj -> obj as JsonObject
      Pair(Regex(obj.string("pattern")!!), obj.string("replacement")!!)
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
      this.explodeReplacing(forms = it, formIndex = formIndex, replacements = explodedForms)
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

    AccentsHelper.accentsRegexList.forEach { (regex, replacement) ->
      if (regex.containsMatchIn(form)) {
        val newForm: String = regex.replace(input = form, replacement = replacement)
        explodedForms.add(newForm)
      }
    }

    return explodedForms
  }

  /**
   * Replace each replacement of [replacements] to the form at the given [formIndex] in the [forms] list, obtaining new
   * forms lists.
   *
   * @param forms a list of forms
   * @param formIndex the index of the form to replace, within the [forms]
   * @param replacements the list of replacements
   *
   * @return a list of forms groups
   */
  private fun explodeReplacing(forms: List<String>, formIndex: Int, replacements: List<String>): List<List<String>> {

    return replacements.map { replacement ->
      forms.subList(0, formIndex) + replacement + forms.subList(formIndex + 1, forms.size)
    }
  }
}
