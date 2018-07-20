/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.morphology.Morphology

/**
 * A multi-words expression.
 *
 * @property startToken the index of the first token of the expression
 * @property endToken the index of the last token of the expression
 * @property morphologies the list of possible morphologies of the expression
 */
data class MultiWords(
  override val startToken: Int,
  override val endToken: Int,
  val morphologies: List<Morphology>
) : TokensRange {

  /**
   * @return the JSON object that represents this multi-words expression
   */
  fun toJSON(): JsonObject {
    TODO("make as abstract and implement for each implementation")
  }
}
