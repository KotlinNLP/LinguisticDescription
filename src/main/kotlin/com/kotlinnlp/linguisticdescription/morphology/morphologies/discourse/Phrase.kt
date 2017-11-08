/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse

import com.kotlinnlp.linguisticdescription.morphology.Label

/**
 * The 'phrase' morphology.
 */
sealed class Phrase : Discourse {

  /**
   * The 'affirmative phrase' morphology.
   */
  class Affirmative : Phrase() {

    override val label = Label.PhrasAff
  }

  /**
   * The 'negative phrase' morphology.
   */
  class Negative : Phrase() {

    override val label = Label.PhrasNeg
  }

  /**
   * The 'interrogative phrase' morphology.
   */
  class Interrogative : Phrase() {

    override val label = Label.PhrasInterr
  }

  /**
   * The 'exclamative phrase' morphology.
   */
  class Exclamative : Phrase() {

    override val label = Label.PhrasExclam
  }
}
