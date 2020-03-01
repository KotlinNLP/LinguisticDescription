/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * An ordinal date object.
 *
 * E.g. "The second week of 2015".
 */
sealed class DateOrdinal : SingleDateTime {

  /**
   * The position.
   *
   * @property count the number that represent the ordinal position (-1 = last, 1 = first, 2 = second, etc.)
   */
  sealed class Position(open val count: Int) {

    /**
     * The ordinal position.
     *
     * @property count the number that represent the ordinal position (1 = first, 2 = second, etc.)
     */
    data class Ordinal(override val count: Int) : Position(count) {

      init {
        require(this.count > 0)
      }

      override fun toString() = "n. $count"
    }

    /**
     * The 'last' position.
     *
     * @property count the number that represent the ordinal position (always -1)
     */
    data class Last(override val count: Int = -1) : Position(-1) {

      override fun toString() = "last"
    }
  }

  /**
   * The ordinal position of the date unit.
   */
  abstract val position: Position

  /**
   * The reference date-time.
   */
  abstract val dateTime: DateTimeObj

  /**
   * Whether the reference [dateTime] indicates a whole year (e.g. "the first Monday of the next year").
   */
  private val hasYearReference: Boolean by lazy {

    when (val refDateTime: DateTimeObj = this.dateTime) {
      is DateObj -> refDateTime.month == null
      is DateTimeSimple -> refDateTime.date.month == null
      is Offset.Date -> refDateTime.value.month == null
      else -> false
    }
  }

  /**
   * The date unit as string.
   */
  val dateUnit: String get() = this::class.simpleName!!

  /**
   * Get the string representing this ordinal date in the following standard format:
   *   the POSITION DATE_UNIT of DATE_TIME
   *
   * @return the string representing this ordinal date
   */
  override fun toStandardFormat(): String {

    val dateUnit: String = (this as? Date)?.value?.toString() ?: this.dateUnit.toLowerCase()

    return "the $position '$dateUnit' of '$dateTime'"
  }

  /**
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject = super.toJSON().apply {

    set("position", position.count)

    if (this@DateOrdinal is Date)
      set("date", value.toJSON())
  }

  /**
   * Find the date that matches a given condition for a number of times given by the ordinal [position], starting
   * from a reference date.
   *
   * @param ref the reference date
   * @param incrementByMonths whether to increment the iteration by months instead of days (default = false)
   * @param condition the condition that must return a boolean depending on the iterating date
   *
   * @return the date that matches the condition for the number of times given by the ordinal [position]
   */
  protected fun findBy(ref: LocalDate,
                       incrementByMonths: Boolean = false,
                       condition: (LocalDate) -> Boolean): LocalDate {

    var count = 0
    var iterDate: LocalDate = ref

    iterDate = if (incrementByMonths) iterDate.minusMonths(1) else iterDate.minusDays(1)

    if (this.position.count >= 0) {

      while (count < this.position.count) {

        iterDate = if (incrementByMonths) iterDate.plusMonths(1) else iterDate.plusDays(1)

        if (condition(iterDate)) count++

        if (!this.hasYearReference && iterDate.month != ref.month || this.hasYearReference && iterDate.year != ref.year)
          throw NotGregorianDateTime("Invalid ordinal position " +
            "(${this::class.simpleName} ${this.position} based to the reference date '$ref'")
      }

    } else {

      iterDate = if (this.hasYearReference) iterDate.plusYears(1) else iterDate.plusMonths(1)

      while (!condition(iterDate))
        iterDate = if (incrementByMonths) iterDate.minusMonths(1) else iterDate.minusDays(1)
    }

    return iterDate
  }

  /**
   * An ordinal date of [DateObj] units.
   * The reference [dateTime] can be only a week day, e.g. "The second Monday of August".
   *
   * @property value the date unit value
   */
  data class Date(
    override val startToken: Int,
    override val endToken: Int,
    override val position: Position,
    override val dateTime: DateTimeObj,
    val value: DateObj
  ) : DateOrdinal() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing this date-time expression, respect to the given reference
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val weekDay: Int = this.value.weekDay ?: throw InvalidDateTime("A DateOrdinal of Date units must have a value " +
        "of week days only (e.g. 'The second Monday of August').")

      val refDateTime: LocalDateTime = this.dateTime.toLocalDateTime(ref)
      val refDateDay1: LocalDate = LocalDate.of(refDateTime.year, refDateTime.month, 1)

      val matchingDate: LocalDate = this.findBy(refDateDay1) { it.dayOfWeek.value == weekDay }

      return matchingDate.atStartOfDay()
    }
  }

  /**
   * An ordinal date of 'day' units.
   */
  data class Day(
    override val startToken: Int,
    override val endToken: Int,
    override val position: Position,
    override val dateTime: DateTimeObj
  ) : DateOrdinal() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing this date-time expression, respect to the given reference
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val refDateTime: LocalDateTime = this.dateTime.toLocalDateTime(ref)
      val refDateDay1: LocalDate = LocalDate.of(refDateTime.year, refDateTime.month, 1)

      // Note: all the iterating days must be counted.
      val matchingDate: LocalDate = this.findBy(refDateDay1) { true }

      return matchingDate.atStartOfDay()
    }
  }

  /**
   * An ordinal date of 'week' units.
   */
  data class Week(
    override val startToken: Int,
    override val endToken: Int,
    override val position: Position,
    override val dateTime: DateTimeObj
  ) : DateOrdinal() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing this date-time expression, respect to the given reference
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val refDateTime: LocalDateTime = this.dateTime.toLocalDateTime(ref)
      val refDateDay1: LocalDate = LocalDate.of(refDateTime.year, refDateTime.month, 1)

      // Note: the ref date-time itself counts as first week.
      val matchingDate: LocalDate = this.findBy(refDateDay1) {
        (it.dayOfMonth == refDateDay1.dayOfMonth && it.month == refDateDay1.month) || it.dayOfWeek == DayOfWeek.MONDAY
      }

      return matchingDate.atStartOfDay()
    }
  }

  /**
   * An ordinal date of 'weekend' units.
   */
  data class Weekend(
    override val startToken: Int,
    override val endToken: Int,
    override val position: Position,
    override val dateTime: DateTimeObj
  ) : DateOrdinal() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing this date-time expression, respect to the given reference
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val refDateTime: LocalDateTime = this.dateTime.toLocalDateTime(ref)
      val refDateDay1: LocalDate = LocalDate.of(refDateTime.year, refDateTime.month, 1)

      val matchingDate: LocalDate = this.findBy(refDateDay1) {
        if (it.dayOfMonth == 1)
          it.dayOfWeek == DayOfWeek.SATURDAY || it.dayOfWeek == DayOfWeek.SUNDAY
        else
          it.dayOfWeek == DayOfWeek.SATURDAY
      }

      return matchingDate.atStartOfDay()
    }
  }

  /**
   * An ordinal date of 'month' units.
   */
  data class Month(
    override val startToken: Int,
    override val endToken: Int,
    override val position: Position,
    override val dateTime: DateTimeObj
  ) : DateOrdinal() {

    /**
     * @return a string representation of this date-time object
     */
    override fun toString(): String = this.toStandardFormat()

    /**
     * @param ref a reference date-time from which to take the missing properties (default = now)
     *
     * @return the LocalDateTime object representing this date-time expression, respect to the given reference
     */
    override fun toLocalDateTime(ref: LocalDateTime): LocalDateTime {

      val refDateTime: LocalDateTime = this.dateTime.toLocalDateTime(ref)
      val refDateDay1: LocalDate = LocalDate.of(refDateTime.year, refDateTime.month, 1)

      // Note: all the iterating months must be counted.
      val matchingDate: LocalDate = this.findBy(ref = refDateDay1, incrementByMonths = true) { true }

      return matchingDate.atStartOfDay()
    }
  }
}
