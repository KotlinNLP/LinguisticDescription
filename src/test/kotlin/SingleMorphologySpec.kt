/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.Conjunction
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Article
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.MissingMorphologyProperty
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 *
 */
class SingleMorphologySpec : Spek({

  describe("a SingleMorphology") {

    context("Morphology with properties") {

      on("factory of an Indefinite Article with Masculine gender, Singular number and Subject case") {

        val morpho = SingleMorphology(
          lemma = "x",
          pos = POS.ArtIndef,
          properties = mapOf(
            "gender" to Gender.Masculine,
            "number" to Number.Singular,
            "case" to GrammaticalCase.Nominative
          ))

        it("should return a Morphology of the expected type") {
          assertTrue { morpho is Article.Indefinite }
        }

        it("should return a Morphology with the expected gender") {
          assertEquals(Gender.Masculine, (morpho as Article.Indefinite).gender)
        }

        it("should return a Morphology with the expected number") {
          assertEquals(Number.Singular, (morpho as Article.Indefinite).number)
        }

        it("should return a Morphology with the expected gender") {
          assertEquals(GrammaticalCase.Nominative, (morpho as Article.Indefinite).case)
        }

        it("should return the expected string calling the toString() method") {
          assertEquals(
            "`x`: Article.Indefinite.Base (case: Nominative, gender: Masculine, number: Singular)",
            morpho.toString())
        }
      }

      on("factory of an Indefinite Article without the 'gender' property") {

        it("should raise a MissingMorphologyProperty") {

          assertFailsWith<MissingMorphologyProperty> {

            SingleMorphology(
              lemma = "x",
              pos = POS.ArtIndef,
              properties = mapOf(
                "number" to Number.Singular,
                "case" to GrammaticalCase.Nominative
              ))
          }
        }
      }

      on("factory of an Indefinite Article without the 'gender' property but allowing incomplete properties") {

        it("should build the expected morphology") {

          val morpho = SingleMorphology(
            lemma = "x",
            pos = POS.ArtIndef,
            properties = mapOf("number" to Number.Singular, "case" to GrammaticalCase.Nominative),
            allowDefaultValues = true)

          val expected = Article.Indefinite.Base(
            lemma = "x",
            oov = false,
            gender = Gender.Undefined,
            number = Number.Singular,
            case = GrammaticalCase.Nominative)

          assertEquals(expected, morpho)
        }
      }
    }

    context("Morphology without properties") {

      on("factory of a Comparative Conjunction") {

        val morpho = SingleMorphology(lemma = "x", pos = POS.ConjCompar)

        it("should return a Morphology of the expected type") {
          assertTrue { morpho is Conjunction.Comparative }
        }

        it("should return the expected string calling the toString() method") {
          assertEquals(
            "`x`: Conjunction.Comparative.Base",
            morpho.toString())
        }
      }

      on("factory of a Comparative Conjunction with extra unnecessary properties") {

        val morpho = SingleMorphology(
          lemma = "x",
          pos = POS.ConjCompar,
          properties = mapOf(
            "gender" to Gender.Masculine,
            "number" to Number.Singular,
            "case" to GrammaticalCase.Nominative
          ))

        it("should return a Morphology of the expected type") {
          assertTrue { morpho is Conjunction.Comparative }
        }
      }
    }

    context("All morphologies") {

      val allProperties = mapOf<String, GrammaticalProperty>( // unnecessary properties will be ignored
        "mood" to Mood.values().first(),
        "tense" to Tense.values().first(),
        "gender" to Gender.values().first(),
        "number" to Number.values().first(),
        "person" to Person.values().first(),
        "case" to GrammaticalCase.values().first(),
        "degree" to Degree.values().first()
      )

      on("factory of all possible Morphologies") {

        it("should create Morphologies of the expected type") {
          assertTrue(POS.values().filterNot { it == POS.Num }.all {
            SingleMorphology(lemma = "x", pos = it, properties = allProperties).pos == it
          })
        }
      }
    }

    context("Num morphology") {

      on("factory") {

        it("should raise an exception") {
          assertFailsWith<MissingMorphologyProperty> {
            SingleMorphology(lemma = "x", pos = POS.Num, properties = mapOf())
          }
        }
      }
    }
  }
})
