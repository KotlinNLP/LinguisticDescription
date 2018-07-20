/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime.intervals

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.SingleDateTime

/**
 * A date-time interval without upper bound.
 *
 * E.g. "".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property from the lower bound date-time
 */
data class OpenToInterval(
  override val startToken: Int,
  override val endToken: Int,
  override val from: SingleDateTime
) : LowerLimitedInterval {

  /**
   * Get the string representing this interval in the following standard format:
   *
   * @return the string representing this interval
   */
  override fun toStandardFormat(): String = "from ${this.from}"

  /**
   * @return a string representation of this interval object
   */
  override fun toString(): String = this.toStandardFormat()
}
