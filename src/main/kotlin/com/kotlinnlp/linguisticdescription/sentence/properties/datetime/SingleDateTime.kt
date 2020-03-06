/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A date-time composed by a single element (not an interval).
 */
interface SingleDateTime : DateTime {

  /**
   * @param ref a reference date-time from which to take the missing properties (default = now)
   *
   * @return the LocalDateTime object representing this date-time expression, respect to the given reference
   */
  fun toLocalDateTime(ref: LocalDateTime = LocalDateTime.now()): LocalDateTime

  /**
   * @param ref a reference date-time from which to take the missing properties (default = now)
   *
   * @return the representation of the date-time expression in the 'TZ' ISO 8601 format 'YYYY-MM-DDThh:mm:ssZ'
   */
  fun isoFormat(ref: LocalDateTime = LocalDateTime.now()): String =
    this.toLocalDateTime(ref).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z"

  /**
   * @return the JSON representation of this object
   */
  override fun toJSON(): JsonObject = super.toJSON().apply {
    set("isoFormat", isoFormat())
  }
}
