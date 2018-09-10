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
 * The 'contin' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Contin(override val direction: SyntaxDependency.Direction) : SyntaxDependency<SyntaxType> {

  /**
   *
   */
  class Base(direction: SyntaxDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.Contin
  }

  /**
   *
   */
  class Denom(direction: SyntaxDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinDenom
  }

  /**
   *
   */
  class Locut(direction: SyntaxDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinLocut
  }

  /**
   *
   */
  class Number(direction: SyntaxDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinNumber
  }

  /**
   *
   */
  class Coord(direction: SyntaxDependency.Direction) : Contin(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinCoord
  }
}
