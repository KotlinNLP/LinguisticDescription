/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties

import com.kotlinnlp.linguisticdescription.InvalidGrammaticalPropertyAnnotation
import com.kotlinnlp.linguisticdescription.InvalidGrammaticalPropertyName

/**
 * The factory of a new [GrammaticalProperty].
 */
object GrammaticalPropertyFactory {

  /**
   * The map of grammatical properties names to maps of annotations to morphology properties.
   */
  private val propertiesMap = mapOf<String, Map<String, GrammaticalProperty>>(
    "mood" to Mood.values().associateBy { it.annotation },
    "tense" to Tense.values().associateBy { it.annotation },
    "gender" to Gender.values().associateBy { it.annotation },
    "number" to Number.values().associateBy { it.annotation },
    "person" to Person.values().associateBy { it.annotation },
    "case" to GrammaticalCase.values().associateBy { it.annotation },
    "degree" to Degree.values().associateBy { it.annotation }
  )

  /**
   * The list of names of all the possible properties.
   */
  val propertyNames: List<String> = this.propertiesMap.keys.toList()

  /**
   * Build a [GrammaticalProperty] given its type and value annotation as strings.
   *
   * @param propertyName the grammatical property name (gender, number, etc...)
   * @param valueAnnotation the annotation string of the value
   *
   * @throws InvalidGrammaticalPropertyName when the [propertyName] is not valid
   * @throws InvalidGrammaticalPropertyAnnotation when the [valueAnnotation] for the given [propertyName] is not valid
   *
   * @return a grammatical property
   */
  operator fun invoke(propertyName: String, valueAnnotation: String): GrammaticalProperty {

    if (propertyName !in this.propertiesMap) throw InvalidGrammaticalPropertyName(propertyName)

    val valuesMap: Map<String, GrammaticalProperty> = this.propertiesMap.getValue(propertyName)

    if (valueAnnotation !in valuesMap)
      throw InvalidGrammaticalPropertyAnnotation(type = propertyName, annotation = valueAnnotation)

    return valuesMap.getValue(valueAnnotation)
  }
}
