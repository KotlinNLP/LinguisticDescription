/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.atemporal

import com.kotlinnlp.linguisticdescription.morphology.Label

/**
 * The 'conjunction' morphology.
 */
sealed class Conjunction : Atemporal {

  /**
   * The 'comparative conjunction' morphology.
   */
  sealed class Comparative : Conjunction() {

    /**
     * The 'comparative conjunction' morphology.
     */
    class Base : Conjunction.Comparative() {

      override val label = Label.ConjCompar
    }

    /**
     * The 'antecedent comparative conjunction' morphology.
     */
    class Antecedent : Conjunction.Comparative() {

      override val label = Label.ConjComparAntec
    }

    /**
     * The 'successive comparative conjunction' morphology.
     */
    class Successive : Conjunction.Comparative() {

      override val label = Label.ConjComparSucc
    }
  }

  /**
   * The 'concessive conjunction' morphology.
   */
  class Concessive : Conjunction() {

    override val label = Label.ConjConcess
  }

  /**
   * The 'coordinating conjunction' morphology.
   */
  sealed class Coordinating : Conjunction() {

    /**
     * The 'coordinating conjunction' morphology.
     */
    class Base : Conjunction.Coordinating() {

      override val label = Label.ConjCoord
    }

    /**
     * The 'adversative coordinating conjunction' morphology.
     */
    class Adversative : Conjunction.Coordinating() {

      override val label = Label.ConjCoordAdvers
    }

    /**
     * The 'disjunctive coordinating conjunction' morphology.
     */
    class Disjunctive : Conjunction.Coordinating() {

      override val label = Label.ConjCoordDisj
    }

    /**
     * The 'explicit coordinating conjunction' morphology.
     */
    class Explicit : Conjunction.Coordinating() {

      override val label = Label.ConjCoordExplic
    }

    /**
     * The 'negative coordinating conjunction' morphology.
     */
    class Negative : Conjunction.Coordinating() {

      override val label = Label.ConjCoordNeg
    }
  }

  /**
   * The 'correlating conjunction' morphology.
   */
  sealed class Correlating : Conjunction() {

    /**
     * The 'correlating conjunction' morphology.
     */
    class Base : Conjunction.Correlating() {

      override val label = Label.ConjCorrel
    }

    /**
     * The 'successive correlating conjunction' morphology.
     */
    class Successive : Conjunction.Correlating() {

      override val label = Label.ConjCorrelSucc
    }

    /**
     * The 'antecedent correlating conjunction' morphology.
     */
    class Antecedent : Conjunction.Correlating() {

      override val label = Label.ConjCorrelAntec
    }
  }

  /**
   * The 'subordinating conjunction' morphology.
   */
  sealed class Subordinating : Conjunction() {

    /**
     * The 'subordinating conjunction' morphology.
     */
    class Base : Conjunction.Subordinating() {

      override val label = Label.ConjSubord
    }

    /**
     * The 'adversative subordinating conjunction' morphology.
     */
    class Adversative : Conjunction.Subordinating() {

      override val label = Label.ConjSubordAdvers
    }

    /**
     * The 'interrogativesubordinating ' morphology.
     */
    class Interrogative : Conjunction.Subordinating() {

      override val label = Label.ConjSubordInterr
    }
  }
}
