/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties

import com.kotlinnlp.linguisticdescription.InvalidMorphologyPropertyAnnotation
import com.kotlinnlp.linguisticdescription.InvalidMorphologyPropertyName

/**
 * The factory of a new [MorphologyProperty].
 */
object MorphologyPropertyFactory {

  /**
   * The map of morphology properties names to maps of annotations to morphology properties.
   */
  private val propertiesMap = mapOf<String, Map<String, MorphologyProperty>>(
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
   * Build a [MorphologyProperty] given its type and value annotation as strings.
   *
   * @param propertyName the morphology property name (gender, number, etc...)
   * @param valueAnnotation the annotation string of the value
   *
   * @throws InvalidMorphologyPropertyName when the [propertyName] is not valid
   * @throws InvalidMorphologyPropertyAnnotation when the [valueAnnotation] for the given [propertyName] is not valid
   *
   * @return a morphology property
   */
  operator fun invoke(propertyName: String, valueAnnotation: String): MorphologyProperty {

    if (propertyName !in this.propertiesMap) throw InvalidMorphologyPropertyName(propertyName)

    val valuesMap: Map<String, MorphologyProperty> = this.propertiesMap.getValue(propertyName)

    if (valueAnnotation !in valuesMap)
      throw InvalidMorphologyPropertyAnnotation(type = propertyName, annotation = valueAnnotation)

    return valuesMap.getValue(valueAnnotation)
  }
}
