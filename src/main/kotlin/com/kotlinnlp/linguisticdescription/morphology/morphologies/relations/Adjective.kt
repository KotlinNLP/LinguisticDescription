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

/**
 * The 'adjective' morphology.
 */
sealed class Adjective : Morphology, Relation {

  /**
   * The 'adjective' morphology.
   */
  class Base : Adjective() {

    override val type: MorphologyType = MorphologyType.Adj
  }

  /**
   * The 'demonstrative adjective' morphology.
   */
  sealed class Demonstrative : Adjective() {

    /**
     * The 'qualifying adjective' morphology.
     */
    class Base : Demonstrative() {

      override val type: MorphologyType = MorphologyType.AdjDemons
    }

    /**
     * The 'antecedent qualifying adjective' morphology.
     */
    class Antecedent : Demonstrative() {

      override val type: MorphologyType = MorphologyType.AdjDemonsAntec
    }

    /**
     * The 'successive qualifying adjective' morphology.
     */
    class Successive : Demonstrative() {

      override val type: MorphologyType = MorphologyType.AdjDemonsSucc
    }
  }

  /**
   * The 'qualifying adjective' morphology.
   */
  sealed class Qualifying : Adjective(), ContentWord {

    /**
     * The 'qualifying adjective' morphology.
     */
    class Base : Qualifying() {

      override val type: MorphologyType = MorphologyType.AdjQualif
    }

    /**
     * The 'postpositive qualifying adjective' morphology.
     */
    class Postpositive : Qualifying() {

      override val type: MorphologyType = MorphologyType.AdjQualifPost
    }
  }

  /**
   * The 'relative adjective' morphology.
   */
  class Relative : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjRelat
  }

  /**
   * The 'exclamative adjective' morphology.
   */
  class Exclamative : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjExclam
  }

  /**
   * The 'indefinite adjective' morphology.
   */
  sealed class Indefinite : Adjective() {

    /**
     * The 'indefinite adjective' morphology.
     */
    class Base : Indefinite() {

      override val type: MorphologyType = MorphologyType.AdjIndef
    }

    /**
     * The 'distributive indefinite adjective' morphology.
     */
    class Distributive : Indefinite() {

      override val type: MorphologyType = MorphologyType.AdjIndefDistr
    }

    /**
     * The 'quantifying indefinite adjective' morphology.
     */
    class Quantifying : Indefinite() {

      override val type: MorphologyType = MorphologyType.AdjIndefQuant
    }

    /**
     * The 'subordinating indefinite adjective' morphology.
     */
    class Subordinating : Indefinite() {

      override val type: MorphologyType = MorphologyType.AdjIndefSubord
    }
  }

  /**
   * The 'interrogative adjective' morphology.
   */
  class Interrogative : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjInterr
  }

  /**
   * The 'possessive adjective' morphology.
   */
  class Possessive : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjPoss
  }

  /**
   * The 'ordinal adjective' morphology.
   */
  class Ordinal : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjOrdin
  }

  /**
   * The 'comparative adjective' morphology.
   */
  class Comparative : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjCompar
  }

  /**
   * The 'deictic adjective' morphology.
   */
  class Deictic : Adjective() {

    override val type: MorphologyType = MorphologyType.AdjDeict
  }
}
