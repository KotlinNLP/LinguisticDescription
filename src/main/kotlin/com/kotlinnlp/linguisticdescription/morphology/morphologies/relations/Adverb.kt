/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord

/**
 * The 'adverb' morphology.
 */
sealed class Adverb : Relation {

  /**
   * The 'modal adverb' morphology.
   */
  class Modal : Adverb(), ContentWord {

    override val type: MorphologyType = MorphologyType.AdvModal
  }

  /**
   * The 'adversative adverb' morphology.
   */
  class Adversative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvAdvers
  }

  /**
   * The 'comparative adverb' morphology.
   */
  class Comparative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvCompar
  }

  /**
   * The 'indefinite adverb' morphology.
   */
  class Indefinite : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvIndef
  }

  /**
   * The 'indefinite subordinating adverb' morphology.
   */
  class IndefiniteSubordinating : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvIndefSubord
  }

  /**
   * The 'interrogative adverb' morphology.
   */
  class Interrogative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvInterr
  }

  /**
   * The 'limiting adverb' morphology.
   */
  class Limiting : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvLimit
  }

  /**
   * The 'locative adverb' morphology.
   */
  class Locative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvLoc
  }

  /**
   * The 'negative adverb' morphology.
   */
  class Negative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvNeg
  }

  /**
   * The 'adverbial phrase' morphology.
   */
  class Phrase : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvPhras
  }

  /**
   * The 'quantitative adverb' morphology.
   */
  class Quantitative : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvQuant
  }

  /**
   * The 'strength adverb' morphology.
   */
  sealed class Strength : Adverb() {

    /**
     * The 'strength adverb' morphology.
     */
    class Base : Strength() {

      override val type: MorphologyType = MorphologyType.AdvStreng
    }

    /**
     * The 'negative strength adverb' morphology.
     */
    class Negative : Strength() {

      override val type: MorphologyType = MorphologyType.AdvStrengNeg
    }
  }

  /**
   * The 'time adverb' morphology.
   */
  class Time : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvTime
  }

  /**
   * The 'concessive adverb' morphology.
   */
  class Concessive : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvConcess
  }

  /**
   * The 'deictic adverb' morphology.
   */
  class Deictic : Adverb() {

    override val type: MorphologyType = MorphologyType.AdvDeict
  }
}
