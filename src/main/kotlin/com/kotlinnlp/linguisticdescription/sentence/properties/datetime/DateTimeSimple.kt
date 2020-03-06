/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * A simple date-time object.
 *
 * E.g. "Monday 10th August at 9:15".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property startChar the index of the first char of this expression
 * @property endChar the index of the last char of this expression
 * @property date the date that compose this date-time
 * @property time the time that compose this date-time
 */
data class DateTimeSimple(
  override val startToken: Int,
  override val endToken: Int,
  override val startChar: Int,
  override val endChar: Int,
  val date: Date,
  val time: Time
) : SingleDateTime {

  /**
   * The type of date-time expression.
   */
  override val type: DateTime.Type = DateTime.Type.DateTime

  /**
   * Get the string representing this date-time in the following standard formats:
   *   DD/MM/YYYY hh:mm:ss
   *
   * @return the string representing this date-time
   */
  override fun toStandardFormat(): String = "%s %s".format(this.date.toStandardFormat(), this.time.toStandardFormat())

  /**
   * @return a string representation of this date-time object
   */
  override fun toString(): String = "%s %s".format(this.date, this.time)

  /**
   * @param ref a reference date-time from which to take the missing properties (default = now)
   *
   * @return the LocalDateTime object representing this date-time expression, respect to the given reference
   */
  override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

    val date: LocalDate = this.date.toLocalDateTime(ref).toLocalDate()
    val time: LocalTime = this.time.toLocalDateTime(ref).toLocalTime()

    return date.atTime(time)
  }
}
