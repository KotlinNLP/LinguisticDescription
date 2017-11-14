/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.Relation
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology

/**
 * The 'conjunction' morphology.
 */
sealed class Conjunction : Morphology, Relation {

  /**
   * The 'conjunction' morphology.
   */
  class Base : Conjunction() {

    override val type: MorphologyType = MorphologyType.Conj
  }

  /**
   * The 'comparative conjunction' morphology.
   */
  sealed class Comparative : Conjunction() {

    /**
     * The 'comparative conjunction' morphology.
     */
    class Base : Comparative() {

      override val type: MorphologyType = MorphologyType.ConjCompar
    }

    /**
     * The 'antecedent comparative conjunction' morphology.
     */
    class Antecedent : Comparative() {

      override val type: MorphologyType = MorphologyType.ConjComparAntec
    }

    /**
     * The 'successive comparative conjunction' morphology.
     */
    class Successive : Comparative() {

      override val type: MorphologyType = MorphologyType.ConjComparSucc
    }
  }

  /**
   * The 'concessive conjunction' morphology.
   */
  class Concessive : Conjunction() {

    override val type: MorphologyType = MorphologyType.ConjConcess
  }

  /**
   * The 'coordinating conjunction' morphology.
   */
  sealed class Coordinating : Conjunction() {

    /**
     * The 'coordinating conjunction' morphology.
     */
    class Base : Coordinating() {

      override val type: MorphologyType = MorphologyType.ConjCoord
    }

    /**
     * The 'adversative coordinating conjunction' morphology.
     */
    class Adversative : Coordinating() {

      override val type = MorphologyType.ConjCoordAdvers
    }

    /**
     * The 'disjunctive coordinating conjunction' morphology.
     */
    class Disjunctive : Coordinating() {

      override val type = MorphologyType.ConjCoordDisj
    }

    /**
     * The 'explicit coordinating conjunction' morphology.
     */
    class Explicit : Coordinating() {

      override val type = MorphologyType.ConjCoordExplic
    }

    /**
     * The 'negative coordinating conjunction' morphology.
     */
    class Negative : Coordinating() {

      override val type = MorphologyType.ConjCoordNeg
    }
  }

  /**
   * The 'correlating conjunction' morphology.
   */
  sealed class Correlating : Conjunction() {

    /**
     * The 'correlating conjunction' morphology.
     */
    class Base : Correlating() {

      override val type = MorphologyType.ConjCorrel
    }

    /**
     * The 'successive correlating conjunction' morphology.
     */
    class Successive : Correlating() {

      override val type = MorphologyType.ConjCorrelSucc
    }

    /**
     * The 'antecedent correlating conjunction' morphology.
     */
    class Antecedent : Correlating() {

      override val type = MorphologyType.ConjCorrelAntec
    }
  }

  /**
   * The 'subordinating conjunction' morphology.
   */
  sealed class Subordinating : Conjunction() {

    /**
     * The 'subordinating conjunction' morphology.
     */
    class Base : Subordinating() {

      override val type = MorphologyType.ConjSubord
    }

    /**
     * The 'adversative subordinating conjunction' morphology.
     */
    class Adversative : Subordinating() {

      override val type = MorphologyType.ConjSubordAdvers
    }

    /**
     * The 'interrogativesubordinating ' morphology.
     */
    class Interrogative : Subordinating() {

      override val type = MorphologyType.ConjSubordInterr
    }
  }
}
