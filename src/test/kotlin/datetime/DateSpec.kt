/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.Date
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
class DateSpec : Spek({

  describe("a Date") {

    context("Wednesday") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 3,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-11T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("the 12th") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-12T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-12T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("September") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 9,
        year = null,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-09-01T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-01T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("the 2018") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-01-01T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-01-01T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("September 2018") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 9,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-01T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-01T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("12th September 2018") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 9,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("12th September '18") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 9,
        year = 18,
        yearAbbr = true,
        holiday = null)

      on("yearFull") {

        it("should return the expected full year") {
          assertEquals(2018, date.yearFull)
        }
      }

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-09-12T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("Easter") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = null,
        yearAbbr = true,
        holiday = Date.Holiday.Easter)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-01-01T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-04-01T00:00:00", date.isoFormat(ref))
        }
      }

      on("isoFormat(ref) based on Wednesday 2019-01-16T09:15:10") {

        val ref = LocalDateTime.of(2019, 1, 16, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2019-04-21T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("ChristmasEve") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = null,
        yearAbbr = true,
        holiday = Date.Holiday.ChristmasEve)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-12-24T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-12-24T00:00:00", date.isoFormat(ref))
        }
      }
    }

    context("Christmas") {

      val date = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = null,
        yearAbbr = true,
        holiday = Date.Holiday.Christmas)

      on("isoFormat()") {

        it("should return the expected string in ISO format") {
          assertEquals("0000-12-25T00:00:00", date.isoFormat())
        }
      }

      on("isoFormat(ref) based on Friday 2018-04-13T09:15:10") {

        val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

        it("should return the expected string in ISO format") {
          assertEquals("2018-12-25T00:00:00", date.isoFormat(ref))
        }
      }
    }
  }
})
