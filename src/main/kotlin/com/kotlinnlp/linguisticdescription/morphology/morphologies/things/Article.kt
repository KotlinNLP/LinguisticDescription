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
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.GrammaticalCase
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.CaseDeclinable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable

/**
 * The 'article' morphology.
 *
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property case the 'grammatical case' morphology property
 */
sealed class Article(
  override val gender: Gender,
  override val number: Number,
  override val case: GrammaticalCase
) : Morphology, Thing, Genderable, Numerable, CaseDeclinable {

  /**
   * The 'article' morphology.
   */
  class Base(gender: Gender, number: Number, case: GrammaticalCase)
    : Article(gender = gender, number = number, case = case) {

    override val type: MorphologyType = MorphologyType.Art
  }

  /**
   * The 'definite article' morphology.
   */
  class Definite(gender: Gender, number: Number, case: GrammaticalCase)
    : Article(gender = gender, number = number, case = case) {

    override val type: MorphologyType = MorphologyType.ArtDef
  }

  /**
   * The 'indefinite article' morphology.
   */
  sealed class Indefinite(gender: Gender, number: Number, case: GrammaticalCase)
    : Article(gender = gender, number = number, case = case) {

    /**
     * The 'indefinite article' morphology.
     */
    class Base(gender: Gender, number: Number, case: GrammaticalCase)
      : Article.Indefinite(gender = gender, number = number, case = case) {

      override val type: MorphologyType = MorphologyType.ArtIndef
    }

    /**
     * The 'indefinite partitive article' morphology.
     */
    class Partitive(gender: Gender, number: Number, case: GrammaticalCase)
      : Article.Indefinite(gender = gender, number = number, case = case) {

      override val type: MorphologyType = MorphologyType.ArtIndefPart
    }
  }
}
