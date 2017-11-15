/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.Thing
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

/**
 * The 'pronoun' morphology.
 *
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property person the 'person' morphology property
 * @property case the 'grammatical case' morphology property
 */
sealed class Pronoun(
  override val gender: Gender,
  override val number: Number,
  override val person: Person,
  override val case: GrammaticalCase
) : Morphology, Thing, Genderable, Numerable, PersonDeclinable, CaseDeclinable {

  /**
   * The 'pronoun' morphology.
   */
  class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.Pron
  }

  /**
   * The 'exclamative pronoun' morphology.
   */
  class Exclamative(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.PronExclam
  }

  /**
   * The 'ordinal pronoun' morphology.
   */
  class Ordinal(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.PronOrdin
  }

  /**
   * The 'demonstrative pronoun' morphology.
   */
  class Demonstrative(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.PronDemons
  }

  /**
   * The 'indefinite pronoun' morphology.
   */
  sealed class Indefinite(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    /**
     * The 'indefinite pronoun' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Indefinite(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronIndef
    }

    /**
     * The 'indefinite subordinating pronoun' morphology.
     */
    class Subordinating(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Indefinite(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronIndefSubord
    }

    /**
     * The 'indefinite distributive pronoun' morphology.
     */
    class Distributive(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Indefinite(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronIndefDistr
    }

    /**
     * The 'indefinite quantifying pronoun' morphology.
     */
    class Quantifying(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Indefinite(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronIndefQuant
    }
  }

  /**
   * The 'interrogative pronoun' morphology.
   */
  class Interrogative(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.PronInterr
  }

  /**
   * The 'personal pronoun' morphology.
   */
  sealed class Personal(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    /**
     * The 'personal pronoun' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Personal(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronPers
    }

    /**
     * The 'reflexive personal pronoun' morphology.
     */
    class Reflexive(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Personal(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronPersRefl
    }

    /**
     * The 'variant personal pronoun' morphology.
     */
    class Variant(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Personal(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronPersVariant
    }

    /**
     * The 'enclitic personal pronoun' morphology.
     */
    class Enclitic(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Personal(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronPersEnclit
    }

    /**
     * The 'proclitic personal pronoun' morphology.
     */
    sealed class Proclitic(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun(gender, number, person, case) {

      /**
       * The 'proclitic personal pronoun' morphology.
       */
      class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
        : Pronoun.Personal.Proclitic(gender, number, person, case) {

        override val type: MorphologyType = MorphologyType.PronPersProclit
      }

      /**
       * The 'proclitic reflexive personal pronoun' morphology.
       */
      class Reflexive(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
        : Pronoun.Personal.Proclitic(gender, number, person, case) {

        override val type: MorphologyType = MorphologyType.PronPersProclitRefl
      }

      /**
       * The 'proclitic variant personal pronoun' morphology.
       */
      class Variant(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
        : Pronoun.Personal.Proclitic(gender, number, person, case) {

        override val type: MorphologyType = MorphologyType.PronPersProclitVariant
      }
    }
  }

  /**
   * The 'possessive pronoun' morphology.
   */
  class Possessive(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    override val type: MorphologyType = MorphologyType.PronPoss
  }

  /**
   * The 'relative pronoun' morphology.
   */
  sealed class Relative(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
    : Pronoun(gender, number, person, case) {

    /**
     * The 'relative pronoun' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Relative(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronRelat
    }

    /**
     * The 'relative double pronoun' morphology.
     */
    class Double(gender: Gender, number: Number, person: Person, case: GrammaticalCase)
      : Pronoun.Relative(gender, number, person, case) {

      override val type: MorphologyType = MorphologyType.PronRelatDouble
    }
  }
}
