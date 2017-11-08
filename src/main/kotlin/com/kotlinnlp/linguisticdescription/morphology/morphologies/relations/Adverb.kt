/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.Label
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord

/**
 * The 'adverb' morphology.
 */
sealed class Adverb : Relation {

  /**
   * The 'modal adverb' morphology.
   */
  class Modal : Adverb(), ContentWord {

    override val label = Label.AdvModal
  }

  /**
   * The 'comparative adverb' morphology.
   */
  class Comparative : Adverb() {

    override val label = Label.AdvCompar
  }

  /**
   * The 'indefinite adverb' morphology.
   */
  class Indefinite : Adverb() {

    override val label = Label.AdvIndef
  }

  /**
   * The 'indefinite subordinating adverb' morphology.
   */
  class IndefiniteSubordinating : Adverb() {

    override val label = Label.AdvIndefSubord
  }

  /**
   * The 'interrogative adverb' morphology.
   */
  class Interrogative : Adverb() {

    override val label = Label.AdvInterr
  }

  /**
   * The 'limiting adverb' morphology.
   */
  class Limiting : Adverb() {

    override val label = Label.AdvLimit
  }

  /**
   * The 'locative adverb' morphology.
   */
  class Locative : Adverb() {

    override val label = Label.AdvLoc
  }

  /**
   * The 'negative adverb' morphology.
   */
  class Negative : Adverb() {

    override val label = Label.AdvNeg
  }

  /**
   * The 'adverbial phrase' morphology.
   */
  class Phrase : Adverb() {

    override val label = Label.AdvPhras
  }

  /**
   * The 'quantitative adverb' morphology.
   */
  class Quantitative : Adverb() {

    override val label = Label.AdvQuant
  }

  /**
   * The 'strength adverb' morphology.
   */
  sealed class Strength : Adverb() {

    /**
     * The 'strength adverb' morphology.
     */
    class Base : Strength() {

      override val label = Label.AdvStreng
    }

    /**
     * The 'negative strength adverb' morphology.
     */
    class Negative : Strength() {

      override val label = Label.AdvStrengNeg
    }
  }

  /**
   * The 'time adverb' morphology.
   */
  class Time : Adverb() {

    override val label = Label.AdvTime
  }

  /**
   * The 'concessive adverb' morphology.
   */
  class Concessive : Adverb() {

    override val label = Label.AdvConcess
  }

  /**
   * The 'deictic adverb' morphology.
   */
  class Deictic : Adverb() {

    override val label = Label.AdvDeict
  }
}
