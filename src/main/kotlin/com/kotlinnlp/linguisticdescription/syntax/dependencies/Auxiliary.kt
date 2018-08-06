/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax.dependencies

import com.kotlinnlp.linguisticdescription.syntax.SyntaxDependency
import com.kotlinnlp.linguisticdescription.syntax.SyntaxType

/**
 * The 'auxiliary' dependency.
 */
sealed class Auxiliary : SyntaxDependency {

  /**
   *
   */
  class Base : Auxiliary() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.Aux
  }

  /**
   *
   */
  class Tense : Auxiliary() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.AuxTense
  }

  /**
   *
   */
  class Passive : Auxiliary() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.AuxPassive
  }

  /**
   *
   */
  class Progressive : Auxiliary() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.AuxProgressive
  }
}