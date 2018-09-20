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
 * @property components the list of single morphologies that compose this morphology (one for a Single morphology)
 * @property score the score assigned to this morphology
 */
class ScoredMorphology(list: List<SingleMorphology>, val score: Double): Morphology(list) {

  /**
   * Build a [ScoredMorphology] with only one single morphology.
   *
   * @property components the single morphologies that compose this morphology
   * @property score the score assigned to this morphology
   */
  constructor(morphology: SingleMorphology, score: Double): this(list = listOf(morphology), score = score)

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
   * @param list a new list of single morphologies
   * @param score the new score
   *
   * @return a new scored morphology with values updated
   */
  fun copy(list: List<SingleMorphology>? = null, score: Double? = null) = ScoredMorphology(
    list = list ?: this.components,
    score = score ?: this.score
  )
}
