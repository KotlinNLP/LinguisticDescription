/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject

/**
 * Extension of the [Morphology] with a [score] property.
 *
 * @property type the type of this morphology (Single or Multiple)
 * @property list a list of single morphologies
 * @property score the score assigned to this morphology
 */
class ScoredMorphology(
  type: Type,
  list: List<SingleMorphology>,
  val score: Double
): Morphology(type, list) {

  /**
   * @return the JSON object that represents this morphology
   */
  override fun toJSON(): JsonObject {

    val jsonObject: JsonObject = super.toJSON()

    jsonObject["score"] = this.score

    return jsonObject
  }

  /**
   * Copy this morphology with no or some value updated.
   * Null values will not be replaced.
   *
   * @param type the new type of morphology (Single or Multiple)
   * @param list a new list of single morphologies
   * @param score the new score
   *
   * @return a new scored morphology with values updated
   */
  fun copy(type: Type? = null, list: List<SingleMorphology>? = null, score: Double? = null) = ScoredMorphology(
    type = type ?: this.type,
    list = list ?: this.list,
    score = score ?: this.score
  )
}
