/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology

/**
 * The 'postposition' morphology.
 *
 * @property lemma the lemma
 */
sealed class Postposition(lemma: String) : SingleMorphology(lemma), Relation {

  /**
   * The 'postposition' morphology.
   */
  class Base(lemma: String) : Postposition(lemma) {

    override val pos: POS = POS.Postpos
  }

  /**
   * The 'possessive postposition' morphology.
   */
  class Possessive(lemma: String) : Postposition(lemma) {

    override val pos: POS = POS.PostposPoss
  }
}
