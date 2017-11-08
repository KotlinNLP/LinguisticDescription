/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.Label

/**
 * The 'preposition' morphology.
 */
sealed class Preposition : Relation {

  /**
   * The 'preposition' morphology.
   */
  class Base : Preposition() {

    override val label = Label.Prep
  }

  /**
   * The 'possessive preposition' morphology.
   */
  class Possessive : Preposition() {

    override val label = Label.PrepPoss
  }

  /**
   * The 'articulated preposition' morphology.
   */
  class Articulated : Preposition() {

    override val label = Label.PrepArt
  }
}
