/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.Person as PersonProp // avoid ambiguity with Noun.Proper.Person
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

/**
 * The 'noun' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Noun(pos: POS)
  : SingleMorphology(pos), Thing, ContentWord, Genderable, Numerable, PersonDeclinable, CaseDeclinable, Gradable {

  /**
   * The 'noun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Base(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: PersonProp = PersonProp.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Noun(POS.Noun)

  /**
   * The 'common noun' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Common(pos: POS) : Noun(pos) {

    /**
     * The 'common noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Common(POS.NounCommon)

    /**
     * The 'common quantifying noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Quantifying(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Common(POS.NounCommonQuant)

    /**
     * The 'common gerundive noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Gerundive(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Common(POS.NounCommonGerund)
  }

  /**
   * The 'proper noun' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Proper(pos: POS) : Noun(pos) {

    /**
     * The 'proper noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Proper(POS.NounProper)

    /**
     * The 'person proper noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Person(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Proper(POS.NounProperPer)

    /**
     * The 'organization proper noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Organization(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Proper(POS.NounProperOrg)

    /**
     * The 'location proper noun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Location(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: PersonProp = PersonProp.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Noun.Proper(POS.NounProperLoc)
  }
}
