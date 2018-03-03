/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse

import com.kotlinnlp.linguisticdescription.Discourse
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.Morphology

/**
 * The 'phrase' morphology.
 *
 * @property lemma the lemma
 */
sealed class Phrase(lemma: String) : Morphology(lemma), Discourse {

  /**
   * The 'affirmative phrase' morphology.
   */
  class Affirmative(lemma: String) : Phrase(lemma) {

    override val type: MorphologyType = MorphologyType.PhrasAff
  }

  /**
   * The 'exclamative phrase' morphology.
   */
  class Exclamative(lemma: String) : Phrase(lemma) {

    override val type: MorphologyType = MorphologyType.PhrasExclam
  }

  /**
   * The 'interrogative phrase' morphology.
   */
  class Interrogative(lemma: String) : Phrase(lemma) {

    override val type: MorphologyType = MorphologyType.PhrasInterr
  }

  /**
   * The 'negative phrase' morphology.
   */
  class Negative(lemma: String) : Phrase(lemma) {

    override val type: MorphologyType = MorphologyType.PhrasNeg
  }
}
