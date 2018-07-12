/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology

/**
 * The 'conjunction' morphology.
 *
 * @property lemma the lemma
 */
sealed class Conjunction(lemma: String) : SingleMorphology(lemma), Relation {

  /**
   * The 'conjunction' morphology.
   */
  class Base(lemma: String) : Conjunction(lemma) {

    override val type: MorphologyType = MorphologyType.Conj
  }

  /**
   * The 'comparative conjunction' morphology.
   */
  sealed class Comparative(lemma: String) : Conjunction(lemma) {

    /**
     * The 'comparative conjunction' morphology.
     */
    class Base(lemma: String) : Comparative(lemma) {

      override val type: MorphologyType = MorphologyType.ConjCompar
    }

    /**
     * The 'antecedent comparative conjunction' morphology.
     */
    class Antecedent(lemma: String) : Comparative(lemma) {

      override val type: MorphologyType = MorphologyType.ConjComparAntec
    }

    /**
     * The 'successive comparative conjunction' morphology.
     */
    class Successive(lemma: String) : Comparative(lemma) {

      override val type: MorphologyType = MorphologyType.ConjComparSucc
    }
  }

  /**
   * The 'concessive conjunction' morphology.
   */
  class Concessive(lemma: String) : Conjunction(lemma) {

    override val type: MorphologyType = MorphologyType.ConjConcess
  }

  /**
   * The 'coordinating conjunction' morphology.
   */
  sealed class Coordinating(lemma: String) : Conjunction(lemma) {

    /**
     * The 'coordinating conjunction' morphology.
     */
    class Base(lemma: String) : Coordinating(lemma) {

      override val type: MorphologyType = MorphologyType.ConjCoord
    }

    /**
     * The 'adversative coordinating conjunction' morphology.
     */
    class Adversative(lemma: String) : Coordinating(lemma) {

      override val type = MorphologyType.ConjCoordAdvers
    }

    /**
     * The 'disjunctive coordinating conjunction' morphology.
     */
    class Disjunctive(lemma: String) : Coordinating(lemma) {

      override val type = MorphologyType.ConjCoordDisj
    }

    /**
     * The 'explicit coordinating conjunction' morphology.
     */
    class Explicit(lemma: String) : Coordinating(lemma) {

      override val type = MorphologyType.ConjCoordExplic
    }

    /**
     * The 'negative coordinating conjunction' morphology.
     */
    class Negative(lemma: String) : Coordinating(lemma) {

      override val type = MorphologyType.ConjCoordNeg
    }
  }

  /**
   * The 'correlating conjunction' morphology.
   */
  sealed class Correlating(lemma: String) : Conjunction(lemma) {

    /**
     * The 'correlating conjunction' morphology.
     */
    class Base(lemma: String) : Correlating(lemma) {

      override val type = MorphologyType.ConjCorrel
    }

    /**
     * The 'successive correlating conjunction' morphology.
     */
    class Successive(lemma: String) : Correlating(lemma) {

      override val type = MorphologyType.ConjCorrelSucc
    }

    /**
     * The 'antecedent correlating conjunction' morphology.
     */
    class Antecedent(lemma: String) : Correlating(lemma) {

      override val type = MorphologyType.ConjCorrelAntec
    }
  }

  /**
   * The 'subordinating conjunction' morphology.
   */
  sealed class Subordinating(lemma: String) : Conjunction(lemma) {

    /**
     * The 'subordinating conjunction' morphology.
     */
    class Base(lemma: String) : Subordinating(lemma) {

      override val type = MorphologyType.ConjSubord
    }

    /**
     * The 'adversative subordinating conjunction' morphology.
     */
    class Adversative(lemma: String) : Subordinating(lemma) {

      override val type = MorphologyType.ConjSubordAdvers
    }

    /**
     * The 'interrogativesubordinating ' morphology.
     */
    class Interrogative(lemma: String) : Subordinating(lemma) {

      override val type = MorphologyType.ConjSubordInterr
    }
  }
}
