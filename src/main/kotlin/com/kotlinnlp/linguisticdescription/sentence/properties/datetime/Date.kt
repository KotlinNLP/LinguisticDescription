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
 * A date object.
 * At least one of [day], [weekDay], [month], [year] or [holiday] is not null.
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property day the number of the day in the range [1, 31] (can be null)
 * @property weekDay the number of the week day in the range [1, 7] (can be null)
 * @property month the number of the mont in the range [1, 12] (can be null)
 * @property year the number of the year in the range [0, 9999] (can be null)
 * @property yearAbbr whether the [year] is intended as abbreviated form (e.g. '98 stands for 1998)
 * @property holiday a holiday name (can be null)
 */
data class Date(
  override val startToken: Int,
  override val endToken: Int,
  val day: Int?,
  val weekDay: Int?,
  val month: Int?,
  val year: Int?,
  val yearAbbr: Boolean,
  val holiday: Holiday?
) : SingleDateTime {

  companion object {

    /**
     * The list of week day names.
     */
    val WEEK_DAYS = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
  }

  /**
   * A holiday name.
   *
   * @property month the month of the holiday (can be null)
   * @property day the day of the holiday (can be null)
   */
  enum class Holiday(val month: Int? = null, val day: Int? = null) {
    Christmas(12, 25),
    ChristmasEve(12, 24),
    Easter
  }

  /**
   * The full number of the year.
   * In case of abbreviation, from '0 to '50 it becomes 20XX, otherwise 19XX.
   */
  val yearFull: Int? = this.year?.let {
    if (this.yearAbbr)
      (if (it < 50) 2000 else 1900) + it
    else
      it
  }

  /**
   * Check that at least one property is defined.
   */
  init {
    require(listOf(this.day, this.weekDay, this.month, this.year, this.holiday).any { it != null })
  }

  /**
   * Get the string representing this date in the following standard format: DD/MM/YYYY.
   *
   * @return the string representing this date
   */
  override fun toStandardFormat(): String = "%s/%s/%s".format(
    this.day?.let { "%02d".format(it) } ?: "-",
    this.month?.let { "%02d".format(it) } ?: "-",
    this.year?.let { (if (this.yearAbbr) "%02d" else "%04d").format(it) } ?: "-"
  )

  /**
   * @return the standard string representation of the [weekDay]
   */
  fun weekDayToString(): String = this.weekDay?.let { WEEK_DAYS[it - 1] } ?: "-"

  /**
   * @return a string representation of this date-time object
   */
  override fun toString(): String = "%s %s%s%s".format(
    this.weekDayToString(),
    this.toStandardFormat(),
    if (this.yearAbbr) " (year abbr.)" else "",
    this.holiday?.let { " [$it]" } ?: ""
  )

  /**
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@Date.startToken,
      "endToken" to this@Date.endToken,
      "day" to this@Date.day,
      "weekDay" to this@Date.weekDay?.let { WEEK_DAYS[it - 1] },
      "month" to this@Date.month,
      "year" to this@Date.year,
      "yearAbbr" to this@Date.yearAbbr,
      "holiday" to this@Date.holiday?.toString()
    )
  }

  /**
   * @return the LocalDateTime object representing this date-time expression
   */
  override fun toLocalDateTime(): LocalDateTime {
    TODO("not implemented")
  }
}
