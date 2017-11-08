/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.atemporal

import com.kotlinnlp.linguisticdescription.morphology.Label
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord

/**
 * The 'adjective' morphology.
 */
sealed class Adjective : Atemporal {

  /**
   * The 'demonstrative adjective' morphology.
   */
  class Demonstrative : Adjective() {

    override val label = Label.AdjDemons
  }

  /**
   * The 'qualifying adjective' morphology.
   */
  class Qualifying : Adjective(), ContentWord {

    override val label = Label.AdjQualif
  }

  /**
   * The 'qualifying postpositive adjective' morphology.
   */
  class QualifyingPostpositive : Adjective(), ContentWord {

    override val label = Label.AdjQualifPost
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
  class Indefinite : Adjective() {

    override val label = Label.AdjIndef
  }

  /**
   * The 'indefinite distributive adjective' morphology.
   */
  class IndefiniteDistributive : Adjective() {

    override val label = Label.AdjIndefDistr
  }

  /**
   * The 'indefinite quantifying adjective' morphology.
   */
  class IndefiniteQuantifying : Adjective() {

    override val label = Label.AdjIndefQuant
  }

  /**
   * The 'indefinite subordinating adjective' morphology.
   */
  class IndefiniteSubordinating : Adjective() {

    override val label = Label.AdjIndefSubord
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
