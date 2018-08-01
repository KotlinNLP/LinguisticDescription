/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
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
 * @property lemma the lemma
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property case the 'grammatical case' morphology property
 */
sealed class Article(
  lemma: String,
  override val gender: Gender,
  override val number: Number,
  override val case: GrammaticalCase
) : SingleMorphology(lemma), Thing, Genderable, Numerable, CaseDeclinable {

  /**
   * The 'article' morphology.
   */
  class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
             case: GrammaticalCase = GrammaticalCase.Undefined)
    : Article(lemma, gender, number, case) {

    override val type: MorphologyType = MorphologyType.Art
  }

  /**
   * The 'definite article' morphology.
   */
  class Definite(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                 case: GrammaticalCase = GrammaticalCase.Undefined)
    : Article(lemma, gender, number, case) {

    override val type: MorphologyType = MorphologyType.ArtDef
  }

  /**
   * The 'indefinite article' morphology.
   */
  sealed class Indefinite(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                          case: GrammaticalCase = GrammaticalCase.Undefined)
    : Article(lemma, gender, number, case) {

    /**
     * The 'indefinite article' morphology.
     */
    class Base(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
               case: GrammaticalCase = GrammaticalCase.Undefined)
      : Article.Indefinite(lemma, gender, number, case) {

      override val type: MorphologyType = MorphologyType.ArtIndef
    }

    /**
     * The 'indefinite partitive article' morphology.
     */
    class Partitive(lemma: String, gender: Gender = Gender.Undefined, number: Number = Number.Undefined,
                    case: GrammaticalCase = GrammaticalCase.Undefined)
      : Article.Indefinite(lemma, gender, number, case) {

      override val type: MorphologyType = MorphologyType.ArtIndefPart
    }
  }
}
