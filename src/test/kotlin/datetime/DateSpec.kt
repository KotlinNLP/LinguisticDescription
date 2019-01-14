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
    }

    context("12") {

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
    }

    context("Set") {

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
    }

    context("2018") {

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
    }

    context("Set 2018") {

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
    }

    context("12 Set 2018") {

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
    }

    context("12 Set '18") {

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
    }
  }
})
