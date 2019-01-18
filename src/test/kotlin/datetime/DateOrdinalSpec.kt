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
import kotlin.test.assertFailsWith

/**
 *
 */
class DateOrdinalSpec : Spek({

  describe("a DateOrdinal of Date units") {

    context("The first August of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The first August of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The first 12th August of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The first 12th August of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = 12,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The first Wednesday of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 3,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last Friday of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 5,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-31T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The second Thursday of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 4,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(2))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-09T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The sixth Thursday of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 4,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(6))

      on("isoFormat(ref)") {

        it("should raise a NotGregorianDateTime exception") {
          assertFailsWith<NotGregorianDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The first Sunday of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 7,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-01-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last Sunday of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 7,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-12-31T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The second Thursday of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 4,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(2))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-01-12T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The fifteenth Thursday of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 4,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(15))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-04-13T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The sixtieth Thursday of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val unit = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = 4,
        month = null,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Date(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        value = unit,
        position = DateOrdinal.Position.Ordinal(60))

      on("isoFormat(ref)") {

        it("should raise a NotGregorianDateTime exception") {
          assertFailsWith<NotGregorianDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }
  }

  describe("a DateOrdinal of Days units") {

    context("The 3rd day of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-03T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 32nd day of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(32))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The 3rd day of August 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-08-03T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last day of February 2016 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 2,
        year = 2016,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2016-02-29T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last day of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-12-31T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 366th day of 2018 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Day(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(366))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }
  }

  describe("a DateOrdinal of Weeks units") {

    context("The 3rd week of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-13T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 6th week of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(6))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The 3rd week of August 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-08-14T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last week of February 2016 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 2,
        year = 2016,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2016-02-29T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last week of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-12-25T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 54th week of 2018 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Week(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(54))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }
  }

  describe("a DateOrdinal of Weekend units") {

    context("The 3rd weekend of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-18T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 6th weekend of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(6))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The 3rd weekend of August 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-08-19T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last weekend of February 2016 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 2,
        year = 2016,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2016-02-27T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last weekend of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-12-30T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 55th weekend of 2018 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Weekend(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(55))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }
  }

  describe("a DateOrdinal of Month units") {

    context("The first month of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2018-08-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The first month of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(1))

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-01-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 2nd month of August (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = null,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(6))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The 3rd month of August 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 8,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(3))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }

    context("The last month of February 2016 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = 2,
        year = 2016,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2016-02-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The last month of 2017 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2017,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Last())

      on("isoFormat(ref)") {

        it("should return the expected string in ISO format") {
          assertEquals("2017-12-01T00:00:00", dateOrdinal.isoFormat(ref))
        }
      }
    }

    context("The 13th month of 2018 (based on Friday 2018-04-13T09:15:10)") {

      val ref = LocalDateTime.of(2018, 4, 13, 9, 15, 10)

      val dateRef = Date(
        startToken = 0,
        endToken = 0,
        day = null,
        weekDay = null,
        month = null,
        year = 2018,
        yearAbbr = false,
        holiday = null)

      val dateOrdinal = DateOrdinal.Month(
        startToken = 0,
        endToken = 0,
        dateTime = dateRef,
        position = DateOrdinal.Position.Ordinal(13))

      on("isoFormat(ref)") {

        it("should raise an InvalidDateTime exception") {
          assertFailsWith<InvalidDateTime> { dateOrdinal.isoFormat(ref) }
        }
      }
    }
  }
})
