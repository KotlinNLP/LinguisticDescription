/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyPropertyAnnotation
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyPropertyType
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 *
 */
class MorphologyPropertyFactorySpec : Spek({

  describe("a MorphologyPropertyFactory") {

    on("factory with correct parameters") {

      val property = MorphologyPropertyFactory(propertyType = "gender", valueAnnotation = "M")

      it("should return the expected MorphologyProperty") {
        assertEquals(Gender.Masculine, property)
      }
    }

    on("factory with invalid property type") {

      it("should raise an InvalidMorphologyPropertyType") {

        assertFailsWith<InvalidMorphologyPropertyType> {
          MorphologyPropertyFactory(propertyType = "invalid_type", valueAnnotation = "F")
        }
      }
    }

    on("factory with invalid annotation value") {

      it("should raise an InvalidMorphologyPropertyAnnotation") {

        assertFailsWith<InvalidMorphologyPropertyAnnotation> {
          MorphologyPropertyFactory(propertyType = "gender", valueAnnotation = "T")
        }
      }
    }

    on("factory of all possible MorphologiesProperties") {

      val propertiesMap = mapOf<String, Map<String, MorphologyProperty>>(
        "mood" to Mood.values().associateBy { it.annotation },
        "tense" to Tense.values().associateBy { it.annotation },
        "gender" to Gender.values().associateBy { it.annotation },
        "number" to Number.values().associateBy { it.annotation },
        "person" to Person.values().associateBy { it.annotation },
        "case" to GrammaticalCase.values().associateBy { it.annotation },
        "degree" to Degree.values().associateBy { it.annotation }
      )

      val allProperties = mutableListOf<Pair<String, String>>()

      propertiesMap.forEach { propertyType, properties ->
        properties.forEach { annotation, _ ->
          allProperties.add(propertyType to annotation)
        }
      }

      it("should create the expected properties") {

        assertTrue {
          allProperties.all {
            val property = MorphologyPropertyFactory(propertyType = it.first, valueAnnotation = it.second)
            property == propertiesMap[it.first]!![it.second] !!
          }
        }
      }
    }
  }
})
