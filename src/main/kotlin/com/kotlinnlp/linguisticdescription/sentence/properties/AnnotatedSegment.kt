/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

/**
 * An annotated segment of tokens.
 *
 * @property startToken the index of the first token of this segment, within the input tokens list
 * @property endToken the index of the last token of this segment, within the input tokens list
 * @property annotation the annotation
 * @property score the confidence score
 */
data class AnnotatedSegment(
  override val startToken: Int,
  override val endToken: Int,
  val annotation: String,
  val score: Double
) : TokensRange
