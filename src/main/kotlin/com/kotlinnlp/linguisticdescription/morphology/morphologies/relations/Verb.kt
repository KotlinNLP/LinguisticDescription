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
 * The 'verb' morphology.
 */
sealed class Verb : Morphology, Relation, ContentWord {

  /**
   * The 'verb' morphology.
   */
  class Base : Verb() {

    override val type = MorphologyType.Verb
  }

  /**
   * The 'modal verb' morphology.
   */
  class Modal : Verb() {

    override val type = MorphologyType.VerbModal
  }

  /**
   * The 'auxiliary verb' morphology.
   */
  class Auxiliary : Verb() {

    override val type = MorphologyType.VerbAux
  }
}
