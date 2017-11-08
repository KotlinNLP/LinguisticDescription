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
 * The 'non-finite verb' morphology.
 */
sealed class NonFiniteVerb : Relation, ContentWord {

  /**
   * The 'non-finite verb' morphology.
   */
  class Verb : NonFiniteVerb() {

    override val label = Label.Verb
  }

  /**
   * The 'modal non-finite verb' morphology.
   */
  class Modal : NonFiniteVerb() {

    override val label = Label.VerbModal
  }

  /**
   * The 'auxiliary non-finite verb' morphology.
   */
  class Auxiliary : NonFiniteVerb() {

    override val label = Label.VerbAux
  }
}
