/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology

/**
 * The 'conjunction' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Conjunction(pos: POS) : SingleMorphology(pos), Relation {

  /**
   * The 'conjunction' morphology.
   *
   * @property lemma the lemma
   */
  class Base(override val lemma: String) : Conjunction(POS.Conj)

  /**
   * The 'comparative conjunction' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Comparative(pos: POS) : Conjunction(pos) {

    /**
     * The 'comparative conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Base(override val lemma: String) : Comparative(POS.ConjCompar)

    /**
     * The 'antecedent comparative conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Antecedent(override val lemma: String) : Comparative(POS.ConjComparAntec)

    /**
     * The 'successive comparative conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Successive(override val lemma: String) : Comparative(POS.ConjComparSucc)
  }

  /**
   * The 'concessive conjunction' morphology.
   *
   * @property lemma the lemma
   */
  class Concessive(override val lemma: String) : Conjunction(POS.ConjConcess)

  /**
   * The 'coordinating conjunction' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Coordinating(pos: POS) : Conjunction(pos) {

    /**
     * The 'coordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Base(override val lemma: String) : Coordinating(POS.ConjCoord)

    /**
     * The 'adversative coordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Adversative(override val lemma: String) : Coordinating(POS.ConjCoordAdvers)

    /**
     * The 'disjunctive coordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Disjunctive(override val lemma: String) : Coordinating(POS.ConjCoordDisj)

    /**
     * The 'explicit coordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Explicit(override val lemma: String) : Coordinating(POS.ConjCoordExplic)

    /**
     * The 'negative coordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Negative(override val lemma: String) : Coordinating(POS.ConjCoordNeg)
  }

  /**
   * The 'correlating conjunction' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Correlating(pos: POS) : Conjunction(pos) {

    /**
     * The 'correlating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Base(override val lemma: String) : Correlating(POS.ConjCorrel)

    /**
     * The 'successive correlating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Successive(override val lemma: String) : Correlating(POS.ConjCorrelSucc)

    /**
     * The 'antecedent correlating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Antecedent(override val lemma: String) : Correlating(POS.ConjCorrelAntec)
  }

  /**
   * The 'subordinating conjunction' morphology.
   *
   * @param pos the POS of this morphology
   */
  sealed class Subordinating(pos: POS) : Conjunction(pos) {

    /**
     * The 'subordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Base(override val lemma: String) : Subordinating(POS.ConjSubord)

    /**
     * The 'adversative subordinating conjunction' morphology.
     *
     * @property lemma the lemma
     */
    class Adversative(override val lemma: String) : Subordinating(POS.ConjSubordAdvers)

    /**
     * The 'interrogativesubordinating ' morphology.
     *
     * @property lemma the lemma
     */
    class Interrogative(override val lemma: String) : Subordinating(POS.ConjSubordInterr)
  }
}
