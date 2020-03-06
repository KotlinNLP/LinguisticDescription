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
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDateTime
import kotlin.test.assertEquals

/**
 *
 */
class DateTimeSimpleSpec : Spek({

  describe("a DateTimeSimple") {

    context("12th September at 10:35 (based on Friday 2018-04-13T09:15:10)") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateTime = DateTimeSimple(
        startToken = 0,
        endToken = 0,
        startChar = 0,
        endChar = 0,
        date = Date(
          startToken = 0,
          endToken = 0,
          startChar = 0,
          endChar = 0,
          day = 12,
          weekDay = null,
          month = 9,
          year = null,
          yearAbbr = false,
          holiday = null),
        time = Time(
          startToken = 0,
          endToken = 0,
          startChar = 0,
          endChar = 0,
          hour = 10,
          min = 35,
          sec = null,
          millisec = null,
          generic = null,
          timezone = null))

      context("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T10:35:00Z", dateTime.isoFormat(ref))
        }
      }
    }
  }
})
