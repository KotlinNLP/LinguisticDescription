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
   *
   */
  class Modal : Adverb(), ContentWord

  /**
   *
   */
  class Comparative : Adverb()

  /**
   *
   */
  class Indefinite : Adverb()

  /**
   *
   */
  class Interrogative : Adverb()

  /**
   *
   */
  class Limiting : Adverb()

  /**
   *
   */
  class Locative : Adverb()

  /**
   *
   */
  class Negative : Adverb()

  /**
   *
   */
  class Phrase : Adverb()

  /**
   *
   */
  class Quantitative : Adverb()

  /**
   *
   */
  class Strength : Adverb()

  /**
   *
   */
  class Time : Adverb()

  /**
   *
   */
  class Concessive : Adverb()

  /**
   *
   */
  class Deictic : Adverb()
}
