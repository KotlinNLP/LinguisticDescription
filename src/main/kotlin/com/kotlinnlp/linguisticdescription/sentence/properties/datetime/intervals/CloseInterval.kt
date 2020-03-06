/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime.intervals

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.DateTime
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.SingleDateTime

/**
 * A limited date-time interval.
 *
 * E.g. "".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property startChar the index of the first char of this expression
 * @property endChar the index of the last char of this expression
 * @property from the lower bound date-time
 * @property to the upper bound date-time
 */
data class CloseInterval(
  override val startToken: Int,
  override val endToken: Int,
  override val startChar: Int,
  override val endChar: Int,
  override val from: SingleDateTime,
  override val to: SingleDateTime
) : LowerLimitedInterval, UpperLimitedInterval {

  /**
   * The type of date-time expression.
   */
  override val type: DateTime.Type = DateTime.Type.IntervalClose

  /**
   * Get the string representing this interval in the following standard format:
   *
   * @return the string representing this interval
   */
  override fun toStandardFormat(): String = "from ${this.from} to ${this.to}"

  /**
   * @return a string representation of this interval object
   */
  override fun toString(): String = this.toStandardFormat()
}
