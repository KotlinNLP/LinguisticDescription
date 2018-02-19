/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.MorphologyDictionary
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.Preposition
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.Verb
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Article
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Noun
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 *
 */
class MorphologyDictionarySpec : Spek({

  describe("a MorphologyDictionary") {

    context("loading from JSONL format") {

      val dictionary: MorphologyDictionary = MorphologyDictionary.load(
        filename = MorphologyDictionary::class.java.classLoader.getResource("test_dictionary.jsonl").file)

      on("content") {

        it("should have the expected size") {
          assertEquals(3, dictionary.size)
        }

        it("should contain the 'form1' form") {
          assertNotNull(dictionary["form1"])
        }

        it("should contain the 'form2' form") {
          assertNotNull(dictionary["form2"])
        }

        it("should contain the 'form3.1 form3.2 form3.3' form") {
          assertNotNull(dictionary["form3.1 form3.2 form3.3"])
        }


        it("should return null when trying to get a form not present") {
          assertNull(dictionary["form3"])
        }
      }

      on("the 'form1' form") {

        val entry: MorphologyDictionary.Entry = dictionary["form1"]!!

        it("should have the expected form") {
          assertEquals("form1", entry.form)
        }

        it("should have a null multiple form") {
          assertNull(entry.multipleForm)
        }

        it("should contain 2 morphologies") {
          assertEquals(2, entry.morphologies.size)
        }

        it("should contain a first Single morphology") {
          assertEquals(MorphologyDictionary.MorphologyEntry.Type.Single, entry.morphologies[0].type)
        }

        it("should contain the first expected morphology") {
          assertEquals(
            Noun.Common.Base(
              lemma = "lemma1",
              case = GrammaticalCase.Unknown,
              degree = Degree.Base,
              gender = Gender.Masculine,
              number = Number.Singular,
              person = Person.Third
            ),
            entry.morphologies[0].list.first()
          )
        }

        it("should contain a second Single morphology") {
          assertEquals(MorphologyDictionary.MorphologyEntry.Type.Single, entry.morphologies[1].type)
        }

        it("should contain the second expected morphology") {
          assertEquals(
            Verb.Base(
              lemma = "lemma2",
              gender = Gender.Feminine,
              number = Number.Singular,
              person = Person.Third,
              mood = Mood.Indicative,
              tense = Tense.Present
            ),
            entry.morphologies[1].list.first()
          )
        }
      }

      on("the 'form2' form") {

        val entry: MorphologyDictionary.Entry = dictionary["form2"]!!

        it("should have the expected form") {
          assertEquals("form2", entry.form)
        }

        it("should have a null multiple form") {
          assertNull(entry.multipleForm)
        }

        it("should contain 1 morphology") {
          assertEquals(1, entry.morphologies.size)
        }

        it("should contain a Multiple morphology") {
          assertEquals(MorphologyDictionary.MorphologyEntry.Type.Multiple, entry.morphologies.first().type)
        }

        it("should contain 2 entries in its multiple morphology") {
          assertEquals(2, entry.morphologies.first().list.size)
        }

        it("should contain the expected first entry of its multiple morphology") {
          assertEquals(Preposition.Base(lemma = "lemma3"), entry.morphologies.first().list[0])
        }

        it("should contain the expected second entry of its multiple morphology") {
          assertEquals(
            Article.Base(
              lemma = "lemma4",
              gender = Gender.Feminine,
              number = Number.Plural,
              case = GrammaticalCase.Object
            ),
            entry.morphologies.first().list[1]
          )
        }
      }

      on("the 'form3.1 form3.2 form3.3' form") {

        val entry: MorphologyDictionary.Entry = dictionary["form3.1 form3.2 form3.3"]!!

        it("should have the expected form") {
          assertEquals("form3.1 form3.2 form3.3", entry.form)
        }

        it("should have the expected multiple form") {
          assertEquals(listOf("form3.1", "form3.2", "form3.3"), entry.multipleForm)
        }

        it("should contain 1 morphology") {
          assertEquals(1, entry.morphologies.size)
        }

        it("should contain a Single morphology") {
          assertEquals(MorphologyDictionary.MorphologyEntry.Type.Single, entry.morphologies.first().type)
        }

        it("should contain the expected morphology") {
          assertEquals(Preposition.Base(lemma = "lemma5 lemma6 lemma7"), entry.morphologies.first().list[0])
        }
      }
    }
  }
})
