/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.sentence.properties.TokensRange
import com.kotlinnlp.utils.JSONSerializable

/**
 * A date-time expression.
 */
interface DateTime : TokensRange, JSONSerializable {

  /**
   * The type of date-time expression.
   *
   * @property annotation the annotation used for the JSON serialization
   */
  enum class Type(val annotation: String) {
    Date("DATE"),
    Holiday("HOLIDAY"),
    Time("TIME"),
    DateTime("DATE_TIME"),
    DateOffset("DATE_OFFSET"),
    OrdinalDate("ORDINAL_DATE"),
    OrdinalDay("ORDINAL_DAY"),
    OrdinalWeek("ORDINAL_WEEK"),
    OrdinalWeekend("ORDINAL_WEEKEND"),
    OrdinalMonth("ORDINAL_MONTH"),
    OffsetDate("OFFSET_DATE"),
    OffsetTime("OFFSET_TIME"),
    OffsetHours("OFFSET_HOURS"),
    OffsetQuarterHours("OFFSET_QUARTERHOURS"),
    OffsetHalfHours("OFFSET_HALFHOURS"),
    OffsetMinutes("OFFSET_MINUTES"),
    OffsetSeconds("OFFSET_SECONDS"),
    OffsetDays("OFFSET_DAYS"),
    OffsetWeeks("OFFSET_WEEKS"),
    OffsetWeekends("OFFSET_WEEKENDS"),
    OffsetMonths("OFFSET_MONTHS"),
    OffsetYears("OFFSET_YEARS"),
    IntervalOpenTo("INTERVAL_OPEN_TO"),
    IntervalOpenFrom("INTERVAL_OPEN_FROM"),
    IntervalClose("INTERVAL_CLOSE")
  }

  /**
   * The type of date-time expression.
   */
  val type: Type

  /**
   * Get the string representing this date-time in the standard format.
   *
   * @return the standard string representing this date-time
   */
  fun toStandardFormat(): String

  /**
   * @return the JSON representation of this object
   */
  override fun toJSON(): JsonObject = super.toJSON().apply {
    set("type", type.annotation)
  }
}
