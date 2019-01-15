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
import kotlin.math.abs
import kotlin.math.sign

/**
 * Find the date-time (at the start of the day) with a given month value far from a given reference for a number of
 * occurrences indicated by a given count.
 * The search is done in the future if the count is positive, in the past if it is negative.
 * If the count is 0 the date-time returned is the one in the same year of the reference.
 *
 * @param month the value of a month (in the range [1, 12])
 * @param count the number of occurrences to find
 *
 * @return the date-time with the given month value nearest (respect to the count) to the reference
 */
internal fun LocalDateTime.findNearestByMonth(month: Int, count: Int): LocalDateTime {

  require(month in 1..12) { "The month value must be an integer in the range [1, 12]." }

  return if (count == 0) {

    LocalDate.of(this.year, month, 1).atStartOfDay()

  } else {

    var retDateTime: LocalDate = this.toLocalDate()
    val increment: Long = count.sign.toLong()
    var i = 0

    while (i < abs(count)) {
      retDateTime = retDateTime.plusMonths(increment)
      if (retDateTime.monthValue == month) i++
    }

    retDateTime.atStartOfDay()
  }
}

/**
 * Find the date-time (at the start of the day) with a given week day value far from this reference date-time for a
 * number of occurrences indicated by a given count.
 * The search is done in the future if the count is positive, in the past if it is negative.
 * If the count is 0 the date-time returned is the one in the same week of the reference.
 *
 * @param weekDay the value of a day in the week (in the range [1 (Monday), 7 (Sunday)])
 * @param count the number of occurrences to find
 *
 * @return the date-time with the given week day value nearest (respect to the count) to this date-time
 */
internal fun LocalDateTime.findNearestByWeekDay(weekDay: Int, count: Int): LocalDateTime {

  require(weekDay in 1..7) { "The week day value must be an integer in the range [1, 7]." }

  return if (count == 0 && this.dayOfWeek.value == weekDay) {

    this.toLocalDate().atStartOfDay()

  } else {

    var retDateTime: LocalDate = this.toLocalDate()
    val normCount: Int = if (count == 0) (weekDay - this.dayOfWeek.value).sign else count
    val increment: Long = normCount.sign.toLong()
    var i = 0

    while (i < abs(normCount)) {
      retDateTime = retDateTime.plusDays(increment)
      if (retDateTime.dayOfWeek.value == weekDay) i++
    }

    retDateTime.atStartOfDay()
  }
}

/**
 * Find the date-time (at the start of the day) with a given hour value far from this reference date-time for a number
 * of occurrences indicated by a given count.
 * The search is done in the future if the count is positive, in the past if it is negative.
 * If the count is 0 the date-time returned is the one in the same day of the reference.
 *
 * @param hour the value of a hour (in the range [0, 23])
 * @param count the number of occurrences to find
 *
 * @return the date-time with the given hour value nearest (respect to the count) to this date-time
 */
internal fun LocalDateTime.findNearestByHour(hour: Int, count: Int): LocalDateTime {

  require(hour in 0..23) { "The hour value must be an integer in the range [0, 23]." }

  return if (count == 0) {

    this.toLocalDate().atTime(hour, 0, 0)

  } else {

    var retDateTime: LocalDateTime = this
    val increment: Long = count.sign.toLong()
    var i = 0

    val refNanos: Long = this.toLocalTime().toNanoOfDay()
    val valueNanos: Long = LocalTime.of(hour, 0, 0).toNanoOfDay()

    // If the value is before the reference time in the same hour the last occurrence of the hour is already counted.
    val normCount: Int = if (this.hour == hour && valueNanos < refNanos && count < 0) count + 1 else count

    while (i < abs(normCount)) {
      retDateTime = retDateTime.plusHours(increment)
      if (retDateTime.hour == hour) i++
    }

    retDateTime.toLocalDate().atTime(retDateTime.hour, 0, 0)
  }
}
