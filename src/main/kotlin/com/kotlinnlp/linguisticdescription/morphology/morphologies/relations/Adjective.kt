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
 * The 'adjective' morphology.
 */
sealed class Adjective : Relation {

  /**
   * The 'adjective' morphology.
   */
  class Base : Adjective() {

    override val label = Label.Adj
  }

  /**
   * The 'demonstrative adjective' morphology.
   */
  class Demonstrative : Adjective() {

    override val label = Label.AdjDemons
  }

  /**
   * The 'qualifying adjective' morphology.
   */
  sealed class Qualifying : Adjective(), ContentWord {

    /**
     * The 'qualifying adjective' morphology.
     */
    class Base : Qualifying() {

      override val label = Label.AdjQualif
    }

    /**
     * The 'postpositive qualifying adjective' morphology.
     */
    class Postpositive : Qualifying() {

      override val label = Label.AdjQualifPost
    }
  }

  /**
   * The 'relative adjective' morphology.
   */
  class Relative : Adjective() {

    override val label = Label.AdjRelat
  }

  /**
   * The 'exclamative adjective' morphology.
   */
  class Exclamative : Adjective() {

    override val label = Label.AdjExclam
  }

  /**
   * The 'indefinite adjective' morphology.
   */
  sealed class Indefinite : Adjective() {

    /**
     * The 'indefinite adjective' morphology.
     */
    class Base : Indefinite() {

      override val label = Label.AdjIndef
    }

    /**
     * The 'distributive indefinite adjective' morphology.
     */
    class Distributive : Indefinite() {

      override val label = Label.AdjIndefDistr
    }

    /**
     * The 'quantifying indefinite adjective' morphology.
     */
    class Quantifying : Indefinite() {

      override val label = Label.AdjIndefQuant
    }

    /**
     * The 'subordinating indefinite adjective' morphology.
     */
    class Subordinating : Indefinite() {

      override val label = Label.AdjIndefSubord
    }
  }

  /**
   * The 'interrogative adjective' morphology.
   */
  class Interrogative : Adjective() {

    override val label = Label.AdjInterr
  }

  /**
   * The 'possessive adjective' morphology.
   */
  class Possessive : Adjective() {

    override val label = Label.AdjPoss
  }

  /**
   * The 'ordinal adjective' morphology.
   */
  class Ordinal : Adjective() {

    override val label = Label.AdjOrdin
  }

  /**
   * The 'comparative adjective' morphology.
   */
  class Comparative : Adjective() {

    override val label = Label.AdjCompar
  }

  /**
   * The 'deictic adjective' morphology.
   */
  class Deictic : Adjective() {

    override val label = Label.AdjDeict
  }
}
