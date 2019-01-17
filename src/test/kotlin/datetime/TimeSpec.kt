/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Time
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

/**
 *
 */
class TimeSpec : Spek({

  describe("a Time (based on Friday 2018-04-13T09:15:10)") {

    val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

    context("9h") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:00:00", time.isoFormat(ref))
        }
      }
    }

    context("9m") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = null,
        min = 9,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:09:00", time.isoFormat(ref))
        }
      }
    }

    context("9s") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = null,
        min = null,
        sec = 9,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:09", time.isoFormat(ref))
        }
      }
    }

    context("09:12") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 12,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:12:00", time.isoFormat(ref))
        }
      }
    }

    context("??:12:07") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = null,
        min = 12,
        sec = 7,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:12:07", time.isoFormat(ref))
        }
      }
    }

    context("09:12:07") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 12,
        sec = 7,
        millisec = null,
        generic = null,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:12:07", time.isoFormat(ref))
        }
      }
    }

    context("09:12:07 +02:00") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 12,
        sec = 7,
        millisec = null,
        generic = null,
        timezone = TimeZone.getTimeZone("CAT"))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:12:07", time.isoFormat(ref))
        }
      }
    }

    context("afternoon") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = null,
        min = null,
        sec = null,
        millisec = null,
        generic = Time.Generic.Afternoon,
        timezone = null)

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T15:00:00", time.isoFormat(ref))
        }
      }
    }

    context("afternoon +02:00") {

      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = null,
        min = null,
        sec = null,
        millisec = null,
        generic = Time.Generic.Afternoon,
        timezone = TimeZone.getTimeZone("CAT"))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T13:00:00", time.isoFormat(ref))
        }
      }
    }
  }
})
