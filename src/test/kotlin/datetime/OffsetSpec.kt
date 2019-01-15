/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Date
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Offset
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
class OffsetSpec : Spek({

  describe("an Offset of Date") {

    context("the next August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val offset = Offset.Date(startToken = 0, endToken = 0, units = 1, value = date)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-01T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("+2 12th of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val offset = Offset.Date(startToken = 0, endToken = 0, units = 2, value = date)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2019-08-12T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 12th of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val offset = Offset.Date(startToken = 0, endToken = 0, units = 1, value = date)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-12T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 3rd of March (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 3,
        weekDay = null,
        month = 3,
        year = null,
        yearAbbr = false,
        holiday = null)

      val offset = Offset.Date(startToken = 0, endToken = 0, units = 1, value = date)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2019-03-03T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this 3rd of March (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 3,
        weekDay = null,
        month = 3,
        year = null,
        yearAbbr = false,
        holiday = null)

      val offset = Offset.Date(startToken = 0, endToken = 0, units = 0, value = date)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-03-03T00:00:00", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Time") {

    context("this h9 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this h7 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this h23 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T23:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this 09:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this 09:15 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:00", offset.isoFormat(ref))
        }
      }
    }

    context("+3 09:15 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 3, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-16T09:15:00", offset.isoFormat(ref))
        }
      }
    }

    context("this 09:15:10 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 10,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("this 09:15:11 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 11,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:11", offset.isoFormat(ref))
        }
      }
    }

    context("this 07:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("this 23:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 0, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T23:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last h9 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last h7 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last h23 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T23:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last 09:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last 09:15 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:00", offset.isoFormat(ref))
        }
      }
    }

    context("-3 09:15 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -3, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-11T09:15:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last 09:15:10 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 10,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("the last 09:15:11 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 11,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T09:15:11", offset.isoFormat(ref))
        }
      }
    }

    context("the last 07:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the last 23:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = -1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T23:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next h9 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next h7 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next h23 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = null,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T23:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 09:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T09:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 09:15 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T09:15:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 09:15:10 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 10,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("the next 09:15:11 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 9,
        min = 15,
        sec = 11,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:11", offset.isoFormat(ref))
        }
      }
    }

    context("the next 07:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 7,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T07:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next 23:00 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val time = Time(
        startToken = 0,
        endToken = 0,
        hour = 23,
        min = 0,
        sec = null,
        millisec = null,
        generic = null,
        timezone = null)

      val offset = Offset.Time(startToken = 0, endToken = 0, units = 1, value = time)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T23:00:00", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Hours") {

    context("+0 hours") {

      val offset = Offset.Hours(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 hours") {

      val offset = Offset.Hours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T03:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 3600, offset.toSeconds())
        }
      }
    }

    context("-3 hours") {

      val offset = Offset.Hours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-31T21:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 3600, offset.toSeconds())
        }
      }
    }

    context("+3 hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Hours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T12:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("+3 hours (based on Friday 2018-04-13T23:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 23, 15, 10)
      val offset = Offset.Hours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T02:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Hours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T06:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 hours (based on Friday 2018-04-13T01:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 1, 15, 10)
      val offset = Offset.Hours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T22:15:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of HalfHours") {

    context("+0 half-hours") {

      val offset = Offset.HalfHours(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 half-hours") {

      val offset = Offset.HalfHours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T01:30:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 1800, offset.toSeconds())
        }
      }
    }

    context("-3 half-hours") {

      val offset = Offset.HalfHours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-31T22:30:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 1800, offset.toSeconds())
        }
      }
    }

    context("+3 half-hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.HalfHours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T10:45:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 half-hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.HalfHours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T07:45:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of QuarterHours") {

    context("+0 quarters of hours") {

      val offset = Offset.QuarterHours(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 quarters of hours") {

      val offset = Offset.QuarterHours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:45:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 900, offset.toSeconds())
        }
      }
    }

    context("-3 quarters of hours") {

      val offset = Offset.QuarterHours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-31T23:15:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 900, offset.toSeconds())
        }
      }
    }

    context("+3 quarters of hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.QuarterHours(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T10:00:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 quarters of hours (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.QuarterHours(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T08:30:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Minutes") {

    context("+0 minutes") {

      val offset = Offset.Minutes(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 minutes") {

      val offset = Offset.Minutes(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:03:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 60, offset.toSeconds())
        }
      }
    }

    context("-3 minutes") {

      val offset = Offset.Minutes(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-31T23:57:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 60, offset.toSeconds())
        }
      }
    }

    context("+3 minutes (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Minutes(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:18:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 minutes (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Minutes(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:12:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Seconds") {

    context("+0 seconds") {

      val offset = Offset.Seconds(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 seconds") {

      val offset = Offset.Seconds(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:03", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3, offset.toSeconds())
        }
      }
    }

    context("-3 seconds") {

      val offset = Offset.Seconds(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-31T23:59:57", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3, offset.toSeconds())
        }
      }
    }

    context("+3 seconds (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Seconds(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:13", offset.isoFormat(ref))
        }
      }
    }

    context("-3 seconds (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Seconds(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-13T09:15:07", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Days") {

    context("+0 days") {

      val offset = Offset.Days(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 days") {

      val offset = Offset.Days(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-04T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 86400, offset.toSeconds())
        }
      }
    }

    context("-3 days") {

      val offset = Offset.Days(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-29T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 86400, offset.toSeconds())
        }
      }
    }

    context("+3 days (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Days(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-16T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 days (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Days(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-10T09:15:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Weekends") {

    context("this weekend (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Weekends(startToken = 0, endToken = 0, units = 0)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("the next weekend (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Weekends(startToken = 0, endToken = 0, units = 1)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-14T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("one weekend ago (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Weekends(startToken = 0, endToken = 0, units = -1)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-07T00:00:00", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Weeks") {

    context("+0 weeks") {

      val offset = Offset.Weeks(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 weeks") {

      val offset = Offset.Weeks(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-22T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(3 * 7 * 86400, offset.toSeconds())
        }
      }
    }

    context("-3 weeks") {

      val offset = Offset.Weeks(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-12-11T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 7 * 86400, offset.toSeconds())
        }
      }
    }

    context("+3 weeks (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Weeks(startToken = 0, endToken = 0, units = 3)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-05-04T00:00:00", offset.isoFormat(ref))
        }
      }
    }

    context("-3 weeks (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Weeks(startToken = 0, endToken = 0, units = -3)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-03-23T00:00:00", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Months") {

    context("+0 months") {

      val offset = Offset.Months(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 months") {

      val offset = Offset.Months(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-04-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals((31 + 29 + 31) * 86400, offset.toSeconds())
        }
      }
    }

    context("-3 months") {

      val offset = Offset.Months(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0001-10-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-(31 + 30 + 31) * 86400, offset.toSeconds())
        }
      }
    }

    context("+3 months (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Months(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-07-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("+3 months (based on Friday 2018-10-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 10, 13, 9, 15, 10)
      val offset = Offset.Months(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2019-01-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 months (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Months(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-01-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 months (based on Friday 2018-02-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 2, 13, 9, 15, 10)
      val offset = Offset.Months(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-11-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }
  }

  describe("an Offset of Years") {

    context("+0 years") {

      val offset = Offset.Years(startToken = 0, endToken = 0, units = 0)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(0, offset.toSeconds())
        }
      }
    }

    context("+3 years") {

      val offset = Offset.Years(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0003-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals((365 + 365 + 366) * 86400, offset.toSeconds())
        }
      }
    }

    context("-3 years") {

      val offset = Offset.Years(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("-0003-01-01T00:00:00", offset.isoFormat())
        }
      }

      on("toSeconds()") {

        it("should return the expected number") {
          assertEquals(-3 * 365 * 86400, offset.toSeconds())
        }
      }
    }

    context("+3 years (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Years(startToken = 0, endToken = 0, units = 3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2021-04-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }

    context("-3 years (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)
      val offset = Offset.Years(startToken = 0, endToken = 0, units = -3)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2015-04-13T09:15:10", offset.isoFormat(ref))
        }
      }
    }
  }
})
