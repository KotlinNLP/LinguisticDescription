/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * A date-time that can be interpreted as absolute and converted to a [LocalDateTime].
 * E.g. a specific date (12 Aug 2018) or a specific time of the day (09:15).
 *
 * If some properties of the date-time are not defined (the year, the day, the minute, etc..) they will be set to a
 * predefined default value:
 *   year = 0
 *   month = 1
 *   day = 1
 *   hour = 0
 *   minute = 0
 *   second = 0
 */
interface AbsoluteDateTime : SingleDateTime {

  companion object {

    /**
     * The offset of seconds of the date '0000-01-01T00:00:00Z' respect to the UNIX epoch date '1970-01-01T00:00:00Z'.
     */
    private val secondsOffsetFrom0: Long = LocalDate.of(0, 1, 1).atStartOfDay().toEpochSecond(ZoneOffset.UTC)
  }

  /**
   * @return the LocalDateTime object representing this date-time expression
   */
  fun toLocalDateTime(): LocalDateTime

  /**
   * @return the representation of the date-time expression in the ISO format 'YYYY-MM-DDThh:mm:ss'
   */
  fun isoFormat(): String = this.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

  /**
   * @return the number of total seconds of the date-time as offset from the date '0000-01-01T00:00:00Z'
   */
  fun toSeconds(): Long = this.toLocalDateTime().toEpochSecond(ZoneOffset.UTC) - secondsOffsetFrom0
}