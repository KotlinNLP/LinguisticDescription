/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.kotlinnlp.linguisticdescription.morphology.Morphology

/**
 * A multi-word expression.
 *
 * @property startToken the index of the first token of the expression
 * @property endToken the index of the last token of the expression
 * @property startChar the index of the first char of the expression
 * @property endChar the index of the last char of the expression
 * @property morphologies the list of possible morphologies of the expression
 */
data class MultiWords(
  override val startToken: Int,
  override val endToken: Int,
  override val startChar: Int,
  override val endChar: Int,
  val morphologies: List<Morphology>
) : TokensRange
