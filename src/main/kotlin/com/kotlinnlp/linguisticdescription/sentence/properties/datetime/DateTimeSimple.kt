/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
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
 * @property date the date that compose this date-time
 * @property time the time that compose this date-time
 */
data class DateTimeSimple(
  override val startToken: Int,
  override val endToken: Int,
  val date: Date,
  val time: Time
) : SingleDateTime {

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
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@DateTimeSimple.startToken,
      "endToken" to this@DateTimeSimple.endToken,
      "date" to this@DateTimeSimple.date.toJSON(),
      "time" to this@DateTimeSimple.time.toJSON()
    )
  }

  /**
   * @return the LocalDateTime object representing this date-time expression
   */
  override fun toLocalDateTime(): LocalDateTime {

    val date: LocalDate = this.date.toLocalDateTime().toLocalDate()
    val time: LocalTime = this.time.toLocalDateTime().toLocalTime()

    return date.atTime(time)
  }
}
