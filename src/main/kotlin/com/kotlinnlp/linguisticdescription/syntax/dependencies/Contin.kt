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
 */
sealed class Contin : SyntaxDependency {

  /**
   *
   */
  class Base : Contin() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.Contin
  }

  /**
   *
   */
  class Denom : Contin() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinDenom
  }

  /**
   *
   */
  class Locut : Contin() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinLocut
  }

  /**
   *
   */
  class Number : Contin() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinNumber
  }

  /**
   *
   */
  class Coord : Contin() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ContinCoord
  }
}