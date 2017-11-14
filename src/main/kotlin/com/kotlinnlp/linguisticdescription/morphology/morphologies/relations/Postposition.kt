/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology

/**
 * The 'postposition' morphology.
 */
sealed class Postposition : Morphology, Relation {

  /**
   * The 'postposition' morphology.
   */
  class Base : Postposition() {

    override val type: MorphologyType = MorphologyType.Postpos
  }

  /**
   * The 'possessive postposition' morphology.
   */
  class Possessive : Postposition() {

    override val type: MorphologyType = MorphologyType.PostposPoss
  }
}
