/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.atemporal

import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord

/**
 * The 'adverb' morphology.
 */
sealed class Adverb : Atemporal {

  /**
   * The 'modal adverb' morphology.
   */
  class Modal : Adverb(), ContentWord

  /**
   * The 'comparative adverb' morphology.
   */
  class Comparative : Adverb()

  /**
   * The 'indefinite adverb' morphology.
   */
  class Indefinite : Adverb()

  /**
   * The 'interrogative adverb' morphology.
   */
  class Interrogative : Adverb()

  /**
   * The 'limiting adverb' morphology.
   */
  class Limiting : Adverb()

  /**
   * The 'locative adverb' morphology.
   */
  class Locative : Adverb()

  /**
   * The 'negative adverb' morphology.
   */
  class Negative : Adverb()

  /**
   * The 'adverbial phrase' morphology.
   */
  class Phrase : Adverb()

  /**
   * The 'quantitative adverb' morphology.
   */
  class Quantitative : Adverb()

  /**
   * The 'strength adverb' morphology.
   */
  class Strength : Adverb()

  /**
   * The 'time adverb' morphology.
   */
  class Time : Adverb()

  /**
   * The 'concessive adverb' morphology.
   */
  class Concessive : Adverb()

  /**
   * The 'deictic adverb' morphology.
   */
  class Deictic : Adverb()
}
