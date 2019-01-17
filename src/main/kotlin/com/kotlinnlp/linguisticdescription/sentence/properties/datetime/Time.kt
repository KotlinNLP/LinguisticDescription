/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import java.time.*
import java.util.*

/**
 * A time object.
 * At least one of [sec], [min], [hour] or [generic] is not null.
 *
 * E.g. "9:15", "h 9", "09:15:45", "Morning".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property hour the number of the hour in the range [0, 23] (can be null)
 * @property min the number of the minute in the range [0, 59] (can be null)
 * @property sec the number of the second in the range [0, 59] (can be null)
 * @property millisec the number of the millisecond in the range [0, 999] (can be null)
 * @property generic the generic time
 * @property timezone the timezone (can be null)
 */
data class Time(
  override val startToken: Int,
  override val endToken: Int,
  val hour: Int?,
  val min: Int?,
  val sec: Int?,
  val millisec: Int?,
  val generic: Generic?,
  val timezone: TimeZone?
) : SingleDateTime {

  /**
   * A generic time.
   *
   * @property hour the hour representing this generic time
   */
  enum class Generic(val hour: Int) { Morning(6), Noon(12), Afternoon(15), Evening(18), Night(0) }

  /**
   * Check that at least one required property is defined.
   */
  init {
    require(listOf(this.sec, this.min, this.hour, this.generic).any { it != null })
  }

  /**
   * Get the string representing this time in the following standard format: hh:mm:ss.ms[ TIMEZONE][ (GENERIC)].
   *
   * @return the string representing this time
   */
  override fun toStandardFormat(): String = "%s:%s:%s.%s%s%s".format(
    this.hour?.let { "%02d".format(it) } ?: "-",
    this.min?.let { "%02d".format(it) } ?: "-",
    this.sec?.let { "%02d".format(it) } ?: "-",
    this.millisec?.let { "%02d".format(it) } ?: "-",
    this.timezone?.let { " ${it.toZoneId()}" } ?: "",
    this.generic?.let { " (${it.toString().toLowerCase()})" } ?: ""
  )

  /**
   * @return a string representation of this time object
   */
  override fun toString(): String = this.toStandardFormat()

  /**
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@Time.startToken,
      "endToken" to this@Time.endToken,
      "hour" to this@Time.hour,
      "min" to this@Time.min,
      "sec" to this@Time.sec,
      "millisec" to this@Time.millisec,
      "generic" to this@Time.generic?.toString(),
      "timezone" to this@Time.timezone?.id
    )
  }

  /**
   * @param ref a reference date-time from which to take the missing properties (default = now)
   *
   * @return the LocalDateTime object representing this date-time expression in UTC, respect to the given reference
   */
  override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

    val hour: Int? = this.generic?.hour ?: hour
    val time = LocalTime.of(
      hour ?: ref.hour,
      this.min ?: if (hour == null) ref.minute else 0,
      this.sec ?: if (hour == null && this.min == null) ref.second else 0,
      this.millisec?.let { it * 1000000 } ?: 0)

    val dateTime: LocalDateTime = time.atDate(ref.toLocalDate())
    val zonedDateTime: ZonedDateTime = dateTime.atZone(this.timezone?.toZoneId() ?: ZoneOffset.UTC)
    val utcDateTime: ZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.from(ZoneOffset.UTC))

    return utcDateTime.toLocalDateTime()
  }
}
