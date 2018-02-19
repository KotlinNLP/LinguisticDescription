/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties

import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyPropertyAnnotation
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyPropertyType

/**
 * The factory of a new [MorphologyProperty].
 */
object MorphologyPropertyFactory {

  /**
   * The map of morphology properties types to maps of annotations to morphology properties.
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
   * The list of types of all the possible properties.
   */
  val propertyTypes: List<String> = this.propertiesMap.keys.toList()

  /**
   * Build a [MorphologyProperty] given its type and value annotation as strings.
   *
   * @param propertyType the morphology property type (gender, number, etc...)
   * @param valueAnnotation the annotation string of the value
   *
   * @throws InvalidMorphologyPropertyType when the [propertyType] is not valid
   * @throws InvalidMorphologyPropertyAnnotation when the [valueAnnotation] for the given [propertyType] is not valid
   *
   * @return a morphology property
   */
  operator fun invoke(propertyType: String, valueAnnotation: String): MorphologyProperty {

    if (propertyType !in this.propertiesMap) throw InvalidMorphologyPropertyType(propertyType)

    val valuesMap: Map<String, MorphologyProperty> = this.propertiesMap.getValue(propertyType)

    if (valueAnnotation !in valuesMap)
      throw InvalidMorphologyPropertyAnnotation(type = propertyType, annotation = valueAnnotation)

    return valuesMap.getValue(valueAnnotation)
  }
}
