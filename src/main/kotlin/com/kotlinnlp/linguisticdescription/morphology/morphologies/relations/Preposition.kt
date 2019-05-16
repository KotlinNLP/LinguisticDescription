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
 * @param pos the POS of this morphology
 */
sealed class Preposition(pos: POS) : SingleMorphology(pos), Relation {

  /**
   * The 'preposition' morphology.
   *
   * @property lemma the lemma
   */
  class Base(override val lemma: String) : Preposition(POS.Prep)

  /**
   * The 'articulated preposition' morphology.
   *
   * @property lemma the lemma
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property case the 'grammatical case' morphological property
   */
  class Articulated(
    override val lemma: String,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val case: GrammaticalCase = GrammaticalCase.Undefined
  ) : Preposition(POS.PrepArt), Genderable, Numerable, CaseDeclinable

  /**
   * The 'possessive preposition' morphology.
   */
  class Possessive(override val lemma: String) : Preposition(POS.PrepPoss)

  /**
   * The 'comparative preposition' morphology.
   */
  class Comparative(override val lemma: String) : Preposition(POS.PrepCompar)
}
