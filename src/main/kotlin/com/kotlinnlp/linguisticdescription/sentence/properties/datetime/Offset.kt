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
import kotlin.math.abs

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
   * The absolute value of [units].
   */
  val absUnits: Int by lazy { abs(this.units) }

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
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime {
      throw NotImplementedError("Impossible to convert an offset of date into a LocalDateTime. " +
        "It must be done manually, using a reference date.")
    }
  }

  /**
   * An offset of [TimeObj].
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
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
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime {
      throw NotImplementedError("Impossible to convert an offset of time into a LocalDateTime. " +
        "It must be done manually, using a reference date.")
    }
  }

  /**
   * An offset of hours.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Hours(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusHours(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusHours(this.absUnits.toLong())
  }

  /**
   * An offset of quarters of an hour.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class QuarterHours(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusMinutes(15 * this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusMinutes(15 * this.absUnits.toLong())
  }

  /**
   * An offset of half-hours.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class HalfHours(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusMinutes(30 * this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusMinutes(30 * this.absUnits.toLong())
  }

  /**
   * An offset of minutes.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Minutes(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusMinutes(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusMinutes(this.absUnits.toLong())
  }

  /**
   * An offset of seconds.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Seconds(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusSeconds(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusSeconds(this.absUnits.toLong())
  }

  /**
   * An offset of days.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Days(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusDays(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusDays(this.absUnits.toLong())
  }

  /**
   * An offset of weeks.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Weeks(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusDays(7 * this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusDays(7 * this.absUnits.toLong())
  }

  /**
   * An offset of weekends.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Weekends(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime {
      throw NotImplementedError("Impossible to convert an offset of weekends into a LocalDateTime. " +
        "It must be done manually, using a reference date.")
    }
  }

  /**
   * An offset of months.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Months(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusMonths(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusMonths(this.absUnits.toLong())
  }

  /**
   * An offset of years.
   *
   * @property startToken the index of the first token of this expression
   * @property endToken the index of the last token of this expression
   * @property units the count of offset units, in the range [0, +inf] (e.g. + 2 weeks)
   */
  data class Years(override val startToken: Int, override val endToken: Int, override val units: Int) : Offset() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @return the LocalDateTime object representing this offset
     */
    override fun toLocalDateTime(): LocalDateTime = if (this.units >= 0)
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).plusYears(this.absUnits.toLong())
    else
      LocalDate.of(0, 1, 1).atTime(LocalTime.of(0, 0, 0)).minusYears(this.absUnits.toLong())
  }
}
