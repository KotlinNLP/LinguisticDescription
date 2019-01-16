/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Date
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.DateTimeSimple
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Time
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
class DateTimeSimpleSpec : Spek({

  describe("a DateTimeSimple") {

    context("12th September at 10:35") {

      val dateTime = DateTimeSimple(
        startToken = 0,
        endToken = 0,
        date = Date(
          startToken = 0,
          endToken = 0,
          day = 12,
          weekDay = null,
          month = 9,
          year = null,
          yearAbbr = false,
          holiday = null),
        time = Time(
          startToken = 0,
          endToken = 0,
          hour = 10,
          min = 35,
          sec = null,
          millisec = null,
          generic = null,
          timezone = null))

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-09-12T10:35:00", dateTime.isoFormat())
        }
      }

      on("isoFormat() based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T10:35:00", dateTime.isoFormat(ref))
        }
      }
    }
  }
})
