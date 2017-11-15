/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties

/**
 * The factory of a new [MorphologyProperty].
 */
object MorphologyPropertyFactory {

  /**
   * The map of morphology properties labels to maps of annotations to morphology properties.
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
   * Create a new [MorphologyProperty] given its label and value annotation as strings.
   *
   * @param label the morphology property label (gender, number, etc...)
   * @param valueAnnotation the annotation string of the value
   *
   * @return a new morphology property
   */
  operator fun invoke(label: String, valueAnnotation: String): MorphologyProperty
    = propertiesMap[label]!![valueAnnotation]!!
}
