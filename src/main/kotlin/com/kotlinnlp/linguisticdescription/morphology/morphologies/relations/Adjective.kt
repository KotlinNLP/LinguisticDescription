/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*

/**
 * The 'adjective' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Adjective(pos: POS)
  : SingleMorphology(pos), Relation, Genderable, Numerable, PersonDeclinable, CaseDeclinable, Gradable {

  /**
   * The 'adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Base(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : ContentWord, Adjective(POS.Adj)

  /**
   * The 'comparative adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Comparative(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjCompar)

  /**
   * The 'deictic adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Deictic(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjDeict)

  /**
   * The 'demonstrative adjective' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Demonstrative(pos: POS) : Adjective(pos) {

    /**
     * The 'demonstrative adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Base(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Demonstrative(POS.AdjDemons)

    /**
     * The 'antecedent demonstrative adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Antecedent(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Demonstrative(POS.AdjDemonsAntec)

    /**
     * The 'successive demonstrative adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Successive(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Demonstrative(POS.AdjDemonsSucc)
  }

  /**
   * The 'exclamative adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Exclamative(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjExclam)

  /**
   * The 'indefinite adjective' morphology.
   *
   * @param pos the POS of this morphologyrty
   */
  sealed class Indefinite(pos: POS) : Adjective(pos) {

    /**
     * The 'indefinite adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Base(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Indefinite(POS.AdjIndef)

    /**
     * The 'distributive indefinite adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Distributive(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Indefinite(POS.AdjIndefDistr)

    /**
     * The 'quantifying indefinite adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Quantifying(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Indefinite(POS.AdjIndefQuant)

    /**
     * The 'subordinating indefinite adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Subordinating(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Indefinite(POS.AdjIndefSubord)
  }

  /**
   * The 'interrogative adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Interrogative(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjInterr)

  /**
   * The 'ordinal adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Ordinal(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjOrdin)

  /**
   * The 'possessive adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Possessive(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjPoss)

  /**
   * The 'qualifying adjective' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Qualifying(pos: POS) : ContentWord, Adjective(pos) {

    /**
     * The 'qualifying adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Base(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Qualifying(POS.AdjQualif)

    /**
     * The 'postpositive qualifying adjective' morphology.
     *
     * @property lemma the lemma
     * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property person the 'person' grammatical property
     * @property case the 'grammatical case' grammatical property
     * @property degree the 'degree' grammatical property
     */
    class Postpositive(
      override val lemma: String,
      override val oov: Boolean,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val person: Person = Person.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined,
      override val degree: Degree = Degree.Base
    ) : Adjective.Qualifying(POS.AdjQualifPost)
  }

  /**
   * The 'relative adjective' morphology.
   *
   * @property lemma the lemma
   * @property oov whether this morphology has been automatically generated by the system (Out Of Vocabulary)
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property person the 'person' grammatical property
   * @property case the 'grammatical case' grammatical property
   * @property degree the 'degree' grammatical property
   */
  class Relative(
    override val lemma: String,
    override val oov: Boolean,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined,
    override val degree: Degree = Degree.Base
  ) : Adjective(POS.AdjRelat)
}
