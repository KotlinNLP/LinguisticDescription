/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.time.LocalDateTime
import kotlin.test.assertEquals

/**
 *
 */
class DateOffsetSpec : Spek({

  describe("a DateOffset") {

    context("Monday of the next week (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 1,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOffset = DateOffset(
        startToken = 0,
        endToken = 0,
        dateTime = date,
        offset = Offset.Weeks(startToken = 0, endToken = 0, units = 1))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-16T00:00:00", dateOffset.isoFormat(ref))
        }
      }
    }

    context("3 days after the 12th September 2018 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 9,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      val dateOffset = DateOffset(
        startToken = 0,
        endToken = 0,
        dateTime = date,
        offset = Offset.Days(startToken = 0, endToken = 0, units = 3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-15T00:00:00", dateOffset.isoFormat(ref))
        }
      }
    }

    context("3 days after the 12th September (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 9,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOffset = DateOffset(
        startToken = 0,
        endToken = 0,
        dateTime = date,
        offset = Offset.Days(startToken = 0, endToken = 0, units = 3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-15T00:00:00", dateOffset.isoFormat(ref))
        }
      }
    }
  }
})
