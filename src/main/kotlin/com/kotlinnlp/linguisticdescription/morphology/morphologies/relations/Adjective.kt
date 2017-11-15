/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.Relation
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

/**
 * The 'adjective' morphology.
 *
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property person the 'person' morphology property
 * @property case the 'grammatical case' morphology property
 * @property degree the 'degree' morphology property
 */
sealed class Adjective(
  override val gender: Gender,
  override val number: Number,
  override val person: Person,
  override val case: GrammaticalCase,
  override val degree: Degree
) : Morphology, Relation, Genderable, Numerable, PersonDeclinable, CaseDeclinable, Gradable {

  /**
   * The 'adjective' morphology.
   */
  class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.Adj
  }

  /**
   * The 'demonstrative adjective' morphology.
   */
  sealed class Demonstrative(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    /**
     * The 'qualifying adjective' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Demonstrative(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjDemons
    }

    /**
     * The 'antecedent qualifying adjective' morphology.
     */
    class Antecedent(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Demonstrative(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjDemonsAntec
    }

    /**
     * The 'successive qualifying adjective' morphology.
     */
    class Successive(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Demonstrative(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjDemonsSucc
    }
  }

  /**
   * The 'qualifying adjective' morphology.
   */
  sealed class Qualifying(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree), ContentWord {

    /**
     * The 'qualifying adjective' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Qualifying(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjQualif
    }

    /**
     * The 'postpositive qualifying adjective' morphology.
     */
    class Postpositive(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Qualifying(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjQualifPost
    }
  }

  /**
   * The 'relative adjective' morphology.
   */
  class Relative(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjRelat
  }

  /**
   * The 'exclamative adjective' morphology.
   */
  class Exclamative(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjExclam
  }

  /**
   * The 'indefinite adjective' morphology.
   */
  sealed class Indefinite(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    /**
     * The 'indefinite adjective' morphology.
     */
    class Base(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Indefinite(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjIndef
    }

    /**
     * The 'distributive indefinite adjective' morphology.
     */
    class Distributive(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Indefinite(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjIndefDistr
    }

    /**
     * The 'quantifying indefinite adjective' morphology.
     */
    class Quantifying(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Indefinite(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjIndefQuant
    }

    /**
     * The 'subordinating indefinite adjective' morphology.
     */
    class Subordinating(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
      : Adjective.Indefinite(gender = gender, number = number, person = person, case = case, degree = degree) {

      override val type: MorphologyType = MorphologyType.AdjIndefSubord
    }
  }

  /**
   * The 'interrogative adjective' morphology.
   */
  class Interrogative(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjInterr
  }

  /**
   * The 'possessive adjective' morphology.
   */
  class Possessive(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjPoss
  }

  /**
   * The 'ordinal adjective' morphology.
   */
  class Ordinal(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjOrdin
  }

  /**
   * The 'comparative adjective' morphology.
   */
  class Comparative(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjCompar
  }

  /**
   * The 'deictic adjective' morphology.
   */
  class Deictic(gender: Gender, number: Number, person: Person, case: GrammaticalCase, degree: Degree)
    : Adjective(gender = gender, number = number, person = person, case = case, degree = degree) {

    override val type: MorphologyType = MorphologyType.AdjDeict
  }
}
