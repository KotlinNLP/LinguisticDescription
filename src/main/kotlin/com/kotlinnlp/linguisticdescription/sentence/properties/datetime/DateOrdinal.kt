/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
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

    val dateUnit: String = (this as? DateOrdinal.Date)?.dateTime?.toString() ?: this.dateUnit.toLowerCase()

    return "the $position '$dateUnit' of '$dateTime'"
  }

  /**
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject {

    val jsonObject: JsonObject = json {
      obj(
        "startToken" to this@DateOrdinal.startToken,
        "endToken" to this@DateOrdinal.endToken,
        "position" to this@DateOrdinal.position.count,
        "dateTime" to this@DateOrdinal.dateTime.toJSON(),
        "unit" to this@DateOrdinal.dateUnit
      )
    }

    if (this is DateOrdinal.Date) {
      jsonObject["date"] = this.value.toJSON()
    }

    return jsonObject
  }

  /**
   * An ordinal date of [DateObj] units.
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
      TODO("not implemented")
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
      TODO("not implemented")
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
      TODO("not implemented")
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
      TODO("not implemented")
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
      TODO("not implemented")
    }
  }

  /**
   * An ordinal date of 'year' units.
   */
  data class Year(
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
      TODO("not implemented")
    }
  }
}
