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
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 *
 */
class OffsetSpec : Spek({

  describe("an Offset") {

    context("Offset.Date: the next 12 August") {

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
          assertFailsWith<NotImplementedError> { offset.toLocalDateTime() }
        }
      }
    }

    context("Offset.Time: the next 09:00") {

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
          assertFailsWith<NotImplementedError> { offset.toLocalDateTime() }
        }
      }
    }

    context("Offset.Time: the next weekend") {

      val offset = Offset.Weekends(startToken = 0, endToken = 0, units = 1)

      on("toLocalDateTime()") {

        it("should return the expected string in ISO format") {
          assertFailsWith<NotImplementedError> { offset.toLocalDateTime() }
        }
      }
    }

    context("Offset.Hour: +3 hours") {

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

    context("Offset.Hour: -3 hours") {

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

    context("Offset.HalfHour: +3 half-hours") {

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

    context("Offset.HalfHour: -3 half-hours") {

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

    context("Offset.QuarterHour: +3 quarter of hours") {

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

    context("Offset.QuarterHour: -3 quarter of hours") {

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

    context("Offset.Minute: +3 minutes") {

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

    context("Offset.Minute: -3 minutes") {

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

    context("Offset.Second: +3 seconds") {

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

    context("Offset.Second: -3 seconds") {

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

    context("Offset.Day: +3 days") {

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

    context("Offset.Day: -3 days") {

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

    context("Offset.Month: +3 months") {

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

    context("Offset.Month: -3 months") {

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

    context("Offset.Year: +3 years") {

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

    context("Offset.Year: -3 years") {

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
  }
})
