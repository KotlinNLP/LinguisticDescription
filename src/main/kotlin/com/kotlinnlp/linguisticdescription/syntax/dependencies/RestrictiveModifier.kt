/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax.dependencies

import com.kotlinnlp.linguisticdescription.syntax.SyntacticDependency
import com.kotlinnlp.linguisticdescription.syntax.SyntacticType

/**
 * The 'restrictive modifier' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class RestrictiveModifier(override val direction: SyntacticDependency.Direction) : SyntacticDependency<SyntacticType> {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RMod
  }

  /**
   *
   */
  class Comparative(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RModCompar
  }

  /**
   *
   */
  class Interrogative(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RModInterr
  }

  /**
   *
   */
  class Negative(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RModNeg
  }

  /**
   *
   */
  class Possessive(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RModPoss
  }

  /**
   *
   */
  class Quantitative(direction: SyntacticDependency.Direction) : RestrictiveModifier(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.RModQuant
  }
}
