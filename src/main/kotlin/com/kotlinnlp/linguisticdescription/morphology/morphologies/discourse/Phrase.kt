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
 * @param pos the POS of this morphology
 */
sealed class Phrase(pos: POS) : SingleMorphology(pos), Discourse {

  /**
   * The 'affirmative phrase' morphology.
   *
   * @property lemma the lemma
   */
  class Affirmative(override val lemma: String) : Phrase(POS.PhrasAff)

  /**
   * The 'exclamative phrase' morphology.
   *
   * @property lemma the lemma
   */
  class Exclamative(override val lemma: String) : Phrase(POS.PhrasExclam)

  /**
   * The 'interrogative phrase' morphology.
   *
   * @property lemma the lemma
   */
  class Interrogative(override val lemma: String) : Phrase(POS.PhrasInterr)

  /**
   * The 'negative phrase' morphology.
   *
   * @property lemma the lemma
   */
  class Negative(override val lemma: String) : Phrase(POS.PhrasNeg)
}
