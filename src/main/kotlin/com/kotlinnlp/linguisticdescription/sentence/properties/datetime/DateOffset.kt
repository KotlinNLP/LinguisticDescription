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
 * A date-offset object.
 *
 * E.g. "Monday of next week", "Tomorrow evening".
 *
 * @property startToken the index of the first token of this expression
 * @property endToken the index of the last token of this expression
 * @property dateTime the date-time
 * @property offset the offset of the [dateTime] (e.g. "next week", "tomorrow")
 */
data class DateOffset(
  override val startToken: Int,
  override val endToken: Int,
  val dateTime: SingleDateTime,
  val offset: Offset
) : SingleDateTime {

  /**
   * Get the string representing this date-offset in the following standard format:
   *   DATE-TIME of OFFSET.
   *
   * @return the string representing this date-offset
   */
  override fun toStandardFormat(): String = "%s of %s".format(this.dateTime, this.offset)

  /**
   * @return a string representation of this date-offset object
   */
  override fun toString(): String = this.toStandardFormat()

  /**
   * @return the JSON object that represents this date-time expression
   */
  override fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@DateOffset.startToken,
      "endToken" to this@DateOffset.endToken,
      "dateTime" to this@DateOffset.dateTime.toJSON(),
      "offset" to this@DateOffset.toJSON()
    )
  }

  /**
   * @return the LocalDateTime object representing this date-time expression
   */
  override fun toLocalDateTime(): LocalDateTime =
    this.dateTime.toLocalDateTime().plusSeconds(this.offset.toSeconds())
}
