/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import java.time.LocalDateTime

/**
 * A date-offset object.
 *
 * E.g. "Monday of next week", "Tomorrow evening".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property startChar the index of the first char of this expression
 * @property endChar the index of the last char of this expression
 * @property dateTime the date-time
 * @property offset the offset of the [dateTime] (e.g. "next week", "tomorrow")
 */
data class DateOffset(
  override val startToken: Int,
  override val endToken: Int,
  override val startChar: Int,
  override val endChar: Int,
  val dateTime: SingleDateTime,
  val offset: Offset
) : SingleDateTime {

  /**
   * The type of date-time expression.
   */
  override val type: DateTime.Type = DateTime.Type.DateOffset

  /**
   * Get the string representing this date-offset in the following standard format:
   *   DATE-TIME of OFFSET.
   *
   * @return the string representing this date-offset
   */
  override fun toStandardFormat(): String = "%s of %s".format(this.dateTime, this.offset)

  /**
   * @return a string representation of this date-offset object
   */
  override fun toString(): String = this.toStandardFormat()

  /**
   *
   * @param ref a reference date-time from which to take the missing properties of the [dateTime] (default = now)
   *
   * @return the LocalDateTime object representing this date-time expression
   */
  override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime =
    this.offset.toLocalDateTime(ref = this.dateTime.toLocalDateTime(ref))
}
