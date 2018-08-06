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
 * The 'restrictive modifier' dependency.
 */
sealed class RestrictiveModifier : SyntaxDependency {

  /**
   *
   */
  class Base : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RMod
  }

  /**
   *
   */
  class Comparative : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RModCompar
  }

  /**
   *
   */
  class Interrogative : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RModInterr
  }

  /**
   *
   */
  class Negative : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RModNeg
  }

  /**
   *
   */
  class Possessive : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RModPoss
  }

  /**
   *
   */
  class Quantitative : RestrictiveModifier() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.RModQuant
  }
}