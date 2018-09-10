/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology

/**
 * The 'phrase' morphology.
 *
 * @property lemma the lemma
 */
sealed class Phrase(lemma: String) : SingleMorphology(lemma), Discourse {

  /**
   * The 'affirmative phrase' morphology.
   */
  class Affirmative(lemma: String) : Phrase(lemma) {

    override val pos: POS = POS.PhrasAff
  }

  /**
   * The 'exclamative phrase' morphology.
   */
  class Exclamative(lemma: String) : Phrase(lemma) {

    override val pos: POS = POS.PhrasExclam
  }

  /**
   * The 'interrogative phrase' morphology.
   */
  class Interrogative(lemma: String) : Phrase(lemma) {

    override val pos: POS = POS.PhrasInterr
  }

  /**
   * The 'negative phrase' morphology.
   */
  class Negative(lemma: String) : Phrase(lemma) {

    override val pos: POS = POS.PhrasNeg
  }
}
