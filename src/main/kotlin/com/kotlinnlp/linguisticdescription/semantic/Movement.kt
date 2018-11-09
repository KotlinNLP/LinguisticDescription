/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.semantic

/**
 *
 */
sealed class Movement {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  class Base : Movement() {

    /**
     *
     */
    override val semanticType = SemanticType.Mov
  }

  /**
   *
   */
  class In : Movement() {

    /**
     *
     */
    override val semanticType = SemanticType.MovIn
  }

  /**
   *
   */
  class From : Movement() {

    /**
     *
     */
    override val semanticType = SemanticType.MovFrom
  }

  /**
   *
   */
  class To : Movement() {

    /**
     *
     */
    override val semanticType = SemanticType.MovTo
  }

  /**
   *
   */
  class Trough : Movement() {

    /**
     *
     */
    override val semanticType = SemanticType.MovTrough
  }
}