/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.beust.klaxon.JsonObject

/**
 * Represents a numeric expression recognized.
 *
 * @property startToken the index of the first token of this numeric expression, within the input tokens list
 * @property endToken the index of the last token of this numeric expression, within the input tokens list
 * @property value the numeric value
 * @property asWord the standard representation of the number in letters
 * @property original the original string containing the number
 */
data class Number(
  override val startToken: Int,
  override val endToken: Int,
  val value: kotlin.Number,
  val asWord: String,
  val original: String
) : TokensRange {

  /**
   * @return the JSON object that represents this number
   */
  fun toJSON(): JsonObject {
    TODO("make as abstract and implement for each implementation")
  }
}
