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
class ScoredMorphology(components: List<SingleMorphology>, val score: Double): Morphology(components) {

  /**
   * Build a [ScoredMorphology] with only one single morphology.
   *
   * @property components the single morphologies that compose this morphology
   * @property score the score assigned to this morphology
   */
  constructor(morphology: SingleMorphology, score: Double): this(components = listOf(morphology), score = score)

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
   * @param components a new list of single morphologies
   * @param score the new score
   *
   * @return a new scored morphology with values updated
   */
  fun copy(components: List<SingleMorphology>? = null, score: Double? = null) = ScoredMorphology(
    components = components ?: this.components,
    score = score ?: this.score
  )

  /**
   * Convert this [ScoredMorphology] to a [ScoredSingleMorphology].
   * It is required that the [components] size is 1.
   *
   * @return a new scored single morphology containing the single component of this
   */
  fun toSingle() = ScoredSingleMorphology(value = this.components.single(), score = this.score)
}
