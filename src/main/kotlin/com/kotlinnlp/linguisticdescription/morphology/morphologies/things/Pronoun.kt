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
 * @param pos the POS of this morphology
 */
sealed class Pronoun(pos: POS) : SingleMorphology(pos), Thing, Genderable, Numerable, PersonDeclinable, CaseDeclinable {

  /**
   * The 'pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Base(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.Pron)

  /**
   * The 'demonstrative pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Demonstrative(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.PronDemons)

  /**
   * The 'exclamative pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Exclamative(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.PronExclam)

  /**
   * The 'indefinite pronoun' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Indefinite(pos: POS) : Pronoun(pos) {

    /**
     * The 'indefinite pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Indefinite(POS.PronIndef)

    /**
     * The 'indefinite subordinating pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Subordinating(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Indefinite(POS.PronIndefSubord)

    /**
     * The 'indefinite distributive pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Distributive(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Indefinite(POS.PronIndefDistr)

    /**
     * The 'indefinite quantifying pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Quantifying(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Indefinite(POS.PronIndefQuant)
  }

  /**
   * The 'interrogative pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Interrogative(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.PronInterr)

  /**
   * The 'ordinal pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Ordinal(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.PronOrdin)

  /**
   * The 'personal pronoun' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Personal(pos: POS) : Pronoun(pos) {

    /**
     * The 'personal pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Personal(POS.PronPers)

    /**
     * The 'reflexive personal pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Reflexive(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Personal(POS.PronPersRefl)

    /**
     * The 'variant personal pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Variant(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Personal(POS.PronPersVariant)

    /**
     * The 'enclitic personal pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Enclitic(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Personal(POS.PronPersEnclit)

    /**
     * The 'proclitic personal pronoun' morphology.
     *
     * @param pos the POS of this morphology
     */
    sealed class Proclitic(pos: POS) : Pronoun(pos) {

      /**
       * The 'proclitic personal pronoun' morphology.
       *
       * @property lemma the lemma
       * @property gender the 'gender' morphological property
       * @property number the 'number' morphological property
       * @property person the 'person' morphological property
       * @property case the 'grammatical case' morphological property
       */
      class Base(
        override val lemma: String,
        override val gender: Gender = Gender.Undefined,
        override val number: Number = Number.Undefined,
        override val person: Person = Person.Undefined,
        override val case: GrammaticalCase = GrammaticalCase.Undefined
      ) : Pronoun.Personal.Proclitic(POS.PronPersProclit)

      /**
       * The 'proclitic reflexive personal pronoun' morphology.
       *
       * @property lemma the lemma
       * @property gender the 'gender' morphological property
       * @property number the 'number' morphological property
       * @property person the 'person' morphological property
       * @property case the 'grammatical case' morphological property
       */
      class Reflexive(
        override val lemma: String,
        override val gender: Gender = Gender.Undefined,
        override val number: Number = Number.Undefined,
        override val person: Person = Person.Undefined,
        override val case: GrammaticalCase = GrammaticalCase.Undefined
      ) : Pronoun.Personal.Proclitic(POS.PronPersProclitRefl)

      /**
       * The 'proclitic variant personal pronoun' morphology.
       *
       * @property lemma the lemma
       * @property gender the 'gender' morphological property
       * @property number the 'number' morphological property
       * @property person the 'person' morphological property
       * @property case the 'grammatical case' morphological property
       */
      class Variant(
        override val lemma: String,
        override val gender: Gender = Gender.Undefined,
        override val number: Number = Number.Undefined,
        override val person: Person = Person.Undefined,
        override val case: GrammaticalCase = GrammaticalCase.Undefined
      ) : Pronoun.Personal.Proclitic(POS.PronPersProclitVariant)
    }
  }

  /**
   * The 'possessive pronoun' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Possessive(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Pronoun(POS.PronPoss)

  /**
   * The 'relative pronoun' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Relative(pos: POS) : Pronoun(pos) {

    /**
     * The 'relative pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Relative(POS.PronRelat)

    /**
     * The 'relative double pronoun' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' morphological property
     * @property number the 'number' morphological property
     * @property person the 'person' morphological property
     * @property case the 'grammatical case' morphological property
     */
    class Double(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Pronoun.Relative(POS.PronRelatDouble)
  }
}
