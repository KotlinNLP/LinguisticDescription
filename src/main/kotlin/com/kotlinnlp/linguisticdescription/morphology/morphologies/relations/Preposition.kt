/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.GrammaticalCase
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.CaseDeclinable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable

/**
 * The 'preposition' morphology.
 *
 * @property lemma the lemma
 */
sealed class Preposition(lemma: String) : SingleMorphology(lemma), Relation {

  /**
   * The 'preposition' morphology.
   */
  class Base(lemma: String) : Preposition(lemma) {

    override val pos: POS = POS.Prep
  }

  /**
   * The 'articulated preposition' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphology property
   * @property number the 'number' morphology property
   * @property case the 'grammatical case' morphology property
   */
  class Articulated(
    lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Preposition(lemma), Genderable, Numerable, CaseDeclinable {

    override val pos: POS = POS.PrepArt
  }

  /**
   * The 'possessive preposition' morphology.
   */
  class Possessive(lemma: String) : Preposition(lemma) {

    override val pos: POS = POS.PrepPoss
  }
}
