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
 * The 'contin' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Contin(override val direction: SyntacticDependency.Direction) : SyntacticDependency<SyntacticType> {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.Contin
  }

  /**
   *
   */
  class Denom(direction: SyntacticDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.ContinDenom
  }

  /**
   *
   */
  class Locut(direction: SyntacticDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.ContinLocut
  }

  /**
   *
   */
  class Number(direction: SyntacticDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.ContinNumber
  }

  /**
   *
   */
  class Coord(direction: SyntacticDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.ContinCoord
  }
}
