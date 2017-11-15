/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.Relation
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Gradable

/**
 * The 'adverb' morphology.
 *
 * @property degree the 'degree' morphology property
 */
sealed class Adverb(override val degree: Degree) : Morphology, Relation, Gradable {

  /**
   * The 'modal adverb' morphology.
   */
  class Modal(degree: Degree) : Adverb(degree = degree), ContentWord {

    override val type: MorphologyType = MorphologyType.AdvModal
  }

  /**
   * The 'adversative adverb' morphology.
   */
  class Adversative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvAdvers
  }

  /**
   * The 'comparative adverb' morphology.
   */
  class Comparative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvCompar
  }

  /**
   * The 'indefinite adverb' morphology.
   */
  class Indefinite(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvIndef
  }

  /**
   * The 'indefinite subordinating adverb' morphology.
   */
  class IndefiniteSubordinating(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvIndefSubord
  }

  /**
   * The 'interrogative adverb' morphology.
   */
  class Interrogative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvInterr
  }

  /**
   * The 'limiting adverb' morphology.
   */
  class Limiting(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvLimit
  }

  /**
   * The 'locative adverb' morphology.
   */
  class Locative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvLoc
  }

  /**
   * The 'negative adverb' morphology.
   */
  class Negative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvNeg
  }

  /**
   * The 'adverbial phrase' morphology.
   */
  class Phrase(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvPhras
  }

  /**
   * The 'quantitative adverb' morphology.
   */
  class Quantitative(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvQuant
  }

  /**
   * The 'strength adverb' morphology.
   */
  sealed class Strength(degree: Degree) : Adverb(degree = degree) {

    /**
     * The 'strength adverb' morphology.
     */
    class Base(degree: Degree) : Adverb.Strength(degree = degree) {

      override val type: MorphologyType = MorphologyType.AdvStreng
    }

    /**
     * The 'negative strength adverb' morphology.
     */
    class Negative(degree: Degree) : Adverb.Strength(degree = degree) {

      override val type: MorphologyType = MorphologyType.AdvStrengNeg
    }
  }

  /**
   * The 'time adverb' morphology.
   */
  class Time(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvTime
  }

  /**
   * The 'concessive adverb' morphology.
   */
  class Concessive(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvConcess
  }

  /**
   * The 'deictic adverb' morphology.
   */
  class Deictic(degree: Degree) : Adverb(degree = degree) {

    override val type: MorphologyType = MorphologyType.AdvDeict
  }
}
