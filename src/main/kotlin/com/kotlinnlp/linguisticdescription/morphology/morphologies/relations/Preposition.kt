/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.Morphology
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
sealed class Preposition(lemma: String) : Morphology(lemma), Relation {

  /**
   * The 'preposition' morphology.
   */
  class Base(lemma: String) : Preposition(lemma) {

    override val type: MorphologyType = MorphologyType.Prep
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
    override val gender: Gender,
    override val number: Number,
    override val case: GrammaticalCase
  ) : Preposition(lemma), Genderable, Numerable, CaseDeclinable {

    override val type: MorphologyType = MorphologyType.PrepArt
  }

  /**
   * The 'possessive preposition' morphology.
   */
  class Possessive(lemma: String) : Preposition(lemma) {

    override val type: MorphologyType = MorphologyType.PrepPoss
  }
}
