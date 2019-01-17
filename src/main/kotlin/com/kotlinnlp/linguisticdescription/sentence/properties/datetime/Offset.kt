/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * An offset object.
 *
 * E.g. "Next year", "In three weeks", "The next 10th August".
 */
sealed class Offset : SingleDateTime {

  /**
   * The count of offset units, in the range [0, +inf] (e.g. + 2 weeks).
   */
  abstract val units: Int

  /**
   * The offset unit type.
   */
  val unitType: String get() = this::class.simpleName!!

  /**
   * Get the string representing this offset in the following standard format:
   *   (+|-) UNITS OFFSET_TYPE.
   *
   * @return the string representing this offset
   */
  override fun toStandardFormat(): String = "%+d %s".format(this.units, this.unitType.toLowerCase())

  /**
   * @return the JSON object that represents this offset
   */
  override fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@Offset.startToken,
      "endToken" to this@Offset.endToken,
      "type" to this@Offset.unitType,
      "units" to this@Offset.units
    )
  }

  /**
   * An offset of [DateObj].
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   * @property value the Date value
   */
  data class Date(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int,
    val value: DateObj
  ) : Offset() {

    /**
     * Get the string representing this offset in the following standard format:
     *   (+|-) UNITS DATE.
     *
     * @return the string representing this offset
     */
    override fun toStandardFormat(): String = "%+d %s".format(this.units, this.value)

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties
     *
     * @return the LocalDateTime object representing the date of the reference with this offset applied to it, at start
     *         of the day
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      if (this.value.yearFull != null) {
        // Year defined

        val day: Int = this.value.holiday?.day ?: this.value.day ?: 1
        val month: Int = this.value.holiday?.month ?: this.value.month ?: 1
        val year: Int = this.value.yearFull

        return LocalDate.of(year, month, day).atStartOfDay()

      } else {
        // Year not defined

        val day: Int? = this.value.holiday?.day ?: this.value.day
        val month: Int? = this.value.holiday?.month ?: this.value.month
        val monthRef: LocalDateTime = month?.let { ref.findNearestByMonth(month = it, count = this.units) } ?: ref

        return when {
          day != null -> LocalDate.of(monthRef.year, monthRef.monthValue, day).atStartOfDay()
          this.value.weekDay != null -> monthRef.findNearestByWeekDay(weekDay = this.value.weekDay, count = this.units)
          else -> LocalDate.of(monthRef.year, monthRef.monthValue, 1).atStartOfDay()
        }
      }
    }
  }

  /**
   * An offset of [TimeObj].
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expressrosaion
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   * @property value the Time value
   */
  data class Time(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int,
    val value: TimeObj
  ) : Offset() {

    /**
     * Get the string representing this offset in the following standard format:
     *   (+|-) UNITS TIME.
     *
     * @return the string representing this offset
     */
    override fun toStandardFormat(): String = "%+d %s".format(this.units, this.value)

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val hour: Int = checkNotNull(this.value.generic?.hour ?: this.value.hour) {
        "At least one between the generic time and the hour must be set in a time offset."
      }

      return if (this.value.min != null) {
        // Minute defined

        if (this.units == 0) {

          ref.toLocalDate().atTime(hour, this.value.min, this.value.sec ?: 0)

        } else {

          val timeValue: LocalTime = LocalTime.of(hour, this.value.min, this.value.sec ?: 0)
          val refNanos: Long = ref.toLocalTime().toNanoOfDay()
          val valueNanos: Long = timeValue.toNanoOfDay()

          val dayIncrement: Int = when {
            // if the reference time is before the value (within the day) the first occurrence is in the same day
            refNanos < valueNanos && this.units > 0 -> this.units - 1
            // if the reference time is after the value (within the day) the last occurrence is in the same day
            refNanos > valueNanos && this.units < 0 -> this.units + 1
            else -> this.units
          }

          ref.toLocalDate().atTime(timeValue).plusDays(dayIncrement.toLong())
        }

      } else {
        // Minute not defined (e.g. the last evening)
        ref.findNearestByHour(hour = hour, count = this.units)
      }
    }
  }

  /**
   * An offset of hours.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Hours(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime = ref.plusHours(this.units.toLong())
  }

  /**
   * An offset of quarters of an hour.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class QuarterHours(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime = ref.plusMinutes(15 * this.units.toLong())
  }

  /**
   * An offset of half-hours.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class HalfHours(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime = ref.plusMinutes(30 * this.units.toLong())
  }

  /**
   * An offset of minutes.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Minutes(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime = ref.plusMinutes(this.units.toLong())
  }

  /**
   * An offset of seconds.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Seconds(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime = ref.plusSeconds(this.units.toLong())
  }

  /**
   * An offset of days.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Days(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime =
      ref.toLocalDate().plusDays(this.units.toLong()).atStartOfDay()
  }

  /**
   * An offset of weeks.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Weeks(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime =
      ref.toLocalDate().plusDays(7 * this.units.toLong()).atStartOfDay()
  }

  /**
   * An offset of weekends.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Weekends(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val normRef: LocalDateTime = if (ref.dayOfWeek == DayOfWeek.SUNDAY) ref.minusDays(1) else ref

      return normRef.findNearestByWeekDay(weekDay = DayOfWeek.SATURDAY.value, count = this.units)
    }
  }

  /**
   * An offset of months.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Months(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime =
      ref.toLocalDate().plusMonths(this.units.toLong()).atStartOfDay()
  }

  /**
   * An offset of years.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Years(
    override val startToken: Int,
    override val endToken: Int,
    override val units: Int
  ) : SingleDateTime, Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing the reference date-time with this offset applied to it
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime =
      ref.toLocalDate().plusYears(this.units.toLong()).atStartOfDay()
  }
}
