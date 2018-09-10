/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

/**
 * The 'pronoun' morphology.
 *
 * @property lemma the lemma
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property person the 'person' morphology property
 * @property case the 'grammatical case' morphology property
 */
sealed class Pronoun(
  lemma: String,
  override val gender: Gender,
  override val number: Number,
  override val person: Person,
  override val case: GrammaticalCase
) : SingleMorphology(lemma), Thing, Genderable, Numerable, PersonDeclinable, CaseDeclinable {

  /**
   * The 'pronoun' morphology.
   */
  class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
             person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.Pron
  }

  /**
   * The 'demonstrative pronoun' morphology.
   */
  class Demonstrative(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                      person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.PronDemons
  }

  /**
   * The 'exclamative pronoun' morphology.
   */
  class Exclamative(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                    person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.PronExclam
  }

  /**
   * The 'indefinite pronoun' morphology.
   */
  sealed class Indefinite(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(lemma, gender, number, person, case) {

    /**
     * The 'indefinite pronoun' morphology.
     */
    class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
               person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Indefinite(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronIndef
    }

    /**
     * The 'indefinite subordinating pronoun' morphology.
     */
    class Subordinating(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                        person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Indefinite(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronIndefSubord
    }

    /**
     * The 'indefinite distributive pronoun' morphology.
     */
    class Distributive(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                       person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Indefinite(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronIndefDistr
    }

    /**
     * The 'indefinite quantifying pronoun' morphology.
     */
    class Quantifying(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                      person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Indefinite(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronIndefQuant
    }
  }

  /**
   * The 'interrogative pronoun' morphology.
   */
  class Interrogative(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                      person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.PronInterr
  }

  /**
   * The 'ordinal pronoun' morphology.
   */
  class Ordinal(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.PronOrdin
  }

  /**
   * The 'personal pronoun' morphology.
   */
  sealed class Personal(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(lemma, gender, number, person, case) {

    /**
     * The 'personal pronoun' morphology.
     */
    class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
               person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Personal(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronPers
    }

    /**
     * The 'reflexive personal pronoun' morphology.
     */
    class Reflexive(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                    person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Personal(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronPersRefl
    }

    /**
     * The 'variant personal pronoun' morphology.
     */
    class Variant(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                  person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Personal(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronPersVariant
    }

    /**
     * The 'enclitic personal pronoun' morphology.
     */
    class Enclitic(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                   person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Personal(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronPersEnclit
    }

    /**
     * The 'proclitic personal pronoun' morphology.
     */
    sealed class Proclitic(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun(lemma, gender, number, person, case) {

      /**
       * The 'proclitic personal pronoun' morphology.
       */
      class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                 person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
        : Pronoun.Personal.Proclitic(lemma, gender, number, person, case) {

        override val pos: POS = POS.PronPersProclit
      }

      /**
       * The 'proclitic reflexive personal pronoun' morphology.
       */
      class Reflexive(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                      person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
        : Pronoun.Personal.Proclitic(lemma, gender, number, person, case) {

        override val pos: POS = POS.PronPersProclitRefl
      }

      /**
       * The 'proclitic variant personal pronoun' morphology.
       */
      class Variant(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                    person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
        : Pronoun.Personal.Proclitic(lemma, gender, number, person, case) {

        override val pos: POS = POS.PronPersProclitVariant
      }
    }
  }

  /**
   * The 'possessive pronoun' morphology.
   */
  class Possessive(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                   person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
    : Pronoun(lemma, gender, number, person, case) {

    override val pos: POS = POS.PronPoss
  }

  /**
   * The 'relative pronoun' morphology.
   */
  sealed class Relative(lemma: String, gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(lemma, gender, number, person, case) {

    /**
     * The 'relative pronoun' morphology.
     */
    class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
               person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Relative(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronRelat
    }

    /**
     * The 'relative double pronoun' morphology.
     */
    class Double(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                 person: Person = Person.Undefined, case: GrammaticalCase = GrammaticalCase.Undefined)
      : Pronoun.Relative(lemma, gender, number, person, case) {

      override val pos: POS = POS.PronRelatDouble
    }
  }
}
