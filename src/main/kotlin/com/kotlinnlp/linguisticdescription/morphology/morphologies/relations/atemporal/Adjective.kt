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
   *
   */
  class Demonstrative : Adjective()

  /**
   *
   */
  class Qualificative : Adjective(), ContentWord

  /**
   *
   */
  class Relative : Adjective()

  /**
   *
   */
  class Exclamative : Adjective()

  /**
   *
   */
  class Indefinite : Adjective()

  /**
   *
   */
  class Interrogative : Adjective()

  /**
   *
   */
  class Possessive : Adjective()

  /**
   *
   */
  class Ordinal : Adjective()

  /**
   *
   */
  class Comparative : Adjective()

  /**
   *
   */
  class Deictic : Adjective()
}
