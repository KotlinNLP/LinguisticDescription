/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject

/**
 * A wrapper of a [SingleMorphology] with a [score] property.
 *
 * @property value the single morphology value
 * @property score the score assigned to this morphology
 */
class ScoredSingleMorphology(val value: SingleMorphology, val score: Double) {

  /**
   * @return the JSON object that represents this morphology
   */
  fun toJSON(): JsonObject {

    val jsonObject: JsonObject = this.value.toJSON()

    jsonObject["score"] = this.score

    return jsonObject
  }

  /**
   * Copy this morphology with no or some value updated.
   * Null values will not be replaced.
   *
   * @param value a new single morphology
   * @param score the new score
   *
   * @return a new scored morphology with values updated
   */
  fun copy(value: SingleMorphology? = null, score: Double? = null) = ScoredSingleMorphology(
    value = value ?: this.value,
    score = score ?: this.score
  )
}
