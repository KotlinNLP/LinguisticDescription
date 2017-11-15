/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.Relation
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.GrammaticalCase
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.CaseDeclinable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable

/**
 * The 'preposition' morphology.
 */
sealed class Preposition : Morphology(), Relation {

  /**
   * The 'preposition' morphology.
   */
  class Base : Preposition() {

    override val type: MorphologyType = MorphologyType.Prep
  }

  /**
   * The 'articulated preposition' morphology.
   *
   * @property gender the 'gender' morphology property
   * @property number the 'number' morphology property
   * @property case the 'grammatical case' morphology property
   */
  class Articulated(
    override val gender: Gender,
    override val number: Number,
    override val case: GrammaticalCase
  ) : Preposition(), Genderable, Numerable, CaseDeclinable {

    override val type: MorphologyType = MorphologyType.PrepArt
  }

  /**
   * The 'possessive preposition' morphology.
   */
  class Possessive : Preposition() {

    override val type: MorphologyType = MorphologyType.PrepPoss
  }
}
