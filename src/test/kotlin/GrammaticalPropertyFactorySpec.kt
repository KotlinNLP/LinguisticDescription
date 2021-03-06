/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.InvalidGrammaticalPropertyAnnotation
import com.kotlinnlp.linguisticdescription.InvalidGrammaticalPropertyName
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 *
 */
class GrammaticalPropertyFactorySpec : Spek({

  describe("a GrammaticalPropertyFactory") {

    context("factory with correct parameters") {

      val property = GrammaticalPropertyFactory(propertyName = "gender", valueAnnotation = "M")

      it("should return the expected MorphologyProperty") {
        assertEquals(Gender.Masculine, property)
      }
    }

    context("factory with invalid property name") {

      it("should raise an InvalidGrammaticalPropertyName") {

        assertFailsWith<InvalidGrammaticalPropertyName> {
          GrammaticalPropertyFactory(propertyName = "invalid_name", valueAnnotation = "F")
        }
      }
    }

    context("factory with invalid annotation value") {

      it("should raise an InvalidGrammaticalPropertyAnnotation") {

        assertFailsWith<InvalidGrammaticalPropertyAnnotation> {
          GrammaticalPropertyFactory(propertyName = "gender", valueAnnotation = "T")
        }
      }
    }

    context("factory of all possible MorphologiesProperties") {

      val propertiesMap = mapOf<String, Map<String, GrammaticalProperty>>(
        "mood" to Mood.values().associateBy { it.annotation },
        "tense" to Tense.values().associateBy { it.annotation },
        "gender" to Gender.values().associateBy { it.annotation },
        "number" to Number.values().associateBy { it.annotation },
        "person" to Person.values().associateBy { it.annotation },
        "case" to GrammaticalCase.values().associateBy { it.annotation },
        "degree" to Degree.values().associateBy { it.annotation }
      )

      val allProperties = mutableListOf<Pair<String, String>>()

      propertiesMap.forEach { propertyName, properties ->
        properties.forEach { annotation, _ ->
          allProperties.add(propertyName to annotation)
        }
      }

      it("should create the expected properties") {

        assertTrue {
          allProperties.all {
            val property = GrammaticalPropertyFactory(propertyName = it.first, valueAnnotation = it.second)
            property == propertiesMap[it.first]!![it.second] !!
          }
        }
      }
    }
  }
})
