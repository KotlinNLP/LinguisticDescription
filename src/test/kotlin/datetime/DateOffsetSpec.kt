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
import kotlin.test.assertEquals

/**
 *
 */
class DateOffsetSpec : Spek({

  describe("a DateOffset") {

    context("3 days after the 12 Set 2018") {

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

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-15T00:00:00", dateOffset.isoFormat())
        }
      }
    }

    context("3 days after the 12 Set") {

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

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-09-15T00:00:00", dateOffset.isoFormat())
        }
      }
    }
  }
})
