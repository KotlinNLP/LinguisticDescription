/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.Thing
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

private typealias PersonProperty = Person // avoid ambiguity with Noun.Person

/**
 * The 'noun' morphology.
 *
 * @property lemma the lemma
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property person the 'person' morphology property
 * @property case the 'grammatical case' morphology property
 * @property degree the 'degree' morphology property
 */
sealed class Noun(
  lemma: String,
  override val gender: Gender,
  override val number: Number,
  override val person: Person,
  override val case: GrammaticalCase,
  override val degree: Degree
) : Morphology(lemma), Thing, ContentWord, Genderable, Numerable, PersonDeclinable, CaseDeclinable, Gradable {

  /**
   * The 'noun' morphology.
   */
  class Base(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Noun(lemma, gender, number, person, case, degree) {

    override val type: MorphologyType = MorphologyType.Noun
  }

  /**
   * The 'common noun' morphology.
   */
  sealed class Common(
    lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree
  ) : Noun(lemma, gender, number, person, case, degree) {

    /**
     * The 'common noun' morphology.
     */
    class Base(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Noun.Common(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounCommon
    }

    /**
     * The 'common quantifying noun' morphology.
     */
    class Quantifying(
      lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree
    ) : Noun.Common(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounCommonQuant
    }

    /**
     * The 'common gerundive noun' morphology.
     */
    class Gerundive(
      lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree
    ) : Noun.Common(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounCommonGerund
    }
  }

  /**
   * The 'proper noun' morphology.
   */
  sealed class Proper(
    lemma: String, gender: Gender, number: Number, person: PersonProperty, case: GrammaticalCase, degree: Degree
  ) : Noun(lemma, gender, number, person, case, degree) {

    /**
     * The 'proper noun' morphology.
     */
    class Base(
      lemma: String, gender: Gender, number: Number, person: PersonProperty, case: GrammaticalCase, degree: Degree
    ) : Noun.Proper(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounProper
    }

    /**
     * The 'person proper noun' morphology.
     */
    class Person(
      lemma: String, gender: Gender, number: Number, person: PersonProperty, case: GrammaticalCase, degree: Degree
    ) : Noun.Proper(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounProperPer
    }

    /**
     * The 'organization proper noun' morphology.
     */
    class Organization(
      lemma: String, gender: Gender, number: Number, person: PersonProperty, case: GrammaticalCase, degree: Degree
    ) : Noun.Proper(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounProperOrg
    }

    /**
     * The 'location proper noun' morphology.
     */
    class Location(
      lemma: String, gender: Gender, number: Number, person: PersonProperty, case: GrammaticalCase, degree: Degree
    ) : Noun.Proper(lemma, gender, number, person, case, degree) {

      override val type: MorphologyType = MorphologyType.NounProperLoc
    }
  }
}
