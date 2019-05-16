/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.GrammaticalCase
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.CaseDeclinable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable

/**
 * The 'article' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Article(pos: POS) : SingleMorphology(pos), Thing, Genderable, Numerable, CaseDeclinable {

  /**
   * The 'article' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property case the 'grammatical case' grammatical property
   */
  class Base(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Article(POS.Art)

  /**
   * The 'definite article' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' grammatical property
   * @property number the 'number' grammatical property
   * @property case the 'grammatical case' grammatical property
   */
  class Definite(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Article(POS.ArtDef)

  /**
   * The 'indefinite article' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Indefinite(pos: POS) : Article(pos) {

    /**
     * The 'indefinite article' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property case the 'grammatical case' grammatical property
     */
    class Base(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Article.Indefinite(POS.ArtIndef)

    /**
     * The 'indefinite partitive article' morphology.
     *
     * @property lemma the lemma
     * @property gender the 'gender' grammatical property
     * @property number the 'number' grammatical property
     * @property case the 'grammatical case' grammatical property
     */
    class Partitive(
      override val lemma: String,
      override val gender: Gender = Gender.Undefined,
      override val number: Number = Number.Undefined,
      override val case: GrammaticalCase = GrammaticalCase.Undefined
    ) : Article.Indefinite(POS.ArtIndefPart)
  }
}
