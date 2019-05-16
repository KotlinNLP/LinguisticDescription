/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Gradable

/**
 * The 'adverb' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Adverb(pos: POS) : SingleMorphology(pos), Relation, Gradable {

  /**
   * The 'adversative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Adversative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvAdvers)

  /**
   * The 'comparative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Comparative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvCompar)

  /**
   * The 'concessive adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Concessive(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvConcess)

  /**
   * The 'deictic adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Deictic(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvDeict)

  /**
   * The 'indefinite adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Indefinite(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvIndef)

  /**
   * The 'indefinite subordinating adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class IndefiniteSubordinating(override val lemma: String, override val degree: Degree = Degree.Base)
    : Adverb(POS.AdvIndefSubord)

  /**
   * The 'interrogative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Interrogative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvInterr)

  /**
   * The 'limiting adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Limiting(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvLimit)

  /**
   * The 'locative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Locative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvLoc)

  /**
   * The 'modal adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Modal(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvModal), ContentWord

  /**
   * The 'negative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Negative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvNeg)

  /**
   * The 'adverbial phrase' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Phrase(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvPhras)

  /**
   * The 'quantitative adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Quantitative(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvQuant)

  /**
   * The 'strength adverb' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Strength(pos: POS) : Adverb(pos) {

    /**
     * The 'strength adverb' morphology.
     *
     * @property lemma the lemma
     * @property degree the 'degree' morphological property
     */
    class Base(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb.Strength(POS.AdvStreng)

    /**
     * The 'negative strength adverb' morphology.
     *
     * @property lemma the lemma
     * @property degree the 'degree' morphological property
     */
    class Negative(override val lemma: String, override val degree: Degree = Degree.Base)
      : Adverb.Strength(POS.AdvStrengNeg)
  }

  /**
   * The 'time adverb' morphology.
   *
   * @property lemma the lemma
   * @property degree the 'degree' morphological property
   */
  class Time(override val lemma: String, override val degree: Degree = Degree.Base) : Adverb(POS.AdvTime)
}
