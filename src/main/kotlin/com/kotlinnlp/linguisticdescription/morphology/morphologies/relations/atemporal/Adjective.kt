/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.atemporal

import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord

/**
 * The 'adjective' morphology.
 */
sealed class Adjective : Atemporal {

  /**
   * The 'demonstrative adjective' morphology.
   */
  class Demonstrative : Adjective()

  /**
   * The 'qualifying adjective' morphology.
   */
  class Qualifying : Adjective(), ContentWord

  /**
   * The 'relative adjective' morphology.
   */
  class Relative : Adjective()

  /**
   * The 'exclamative adjective' morphology.
   */
  class Exclamative : Adjective()

  /**
   * The 'indefinite adjective' morphology.
   */
  class Indefinite : Adjective()

  /**
   * The 'interrogative adjective' morphology.
   */
  class Interrogative : Adjective()

  /**
   * The 'possessive adjective' morphology.
   */
  class Possessive : Adjective()

  /**
   * The 'ordinal adjective' morphology.
   */
  class Ordinal : Adjective()

  /**
   * The 'comparative adjective' morphology.
   */
  class Comparative : Adjective()

  /**
   * The 'deictic adjective' morphology.
   */
  class Deictic : Adjective()
}
