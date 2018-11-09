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
sealed class Comparative {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  class Base : Comparative() {

    /**
     *
     */
    override val semanticType = SemanticType.Comparative
  }

  /**
   *
   */
  class Limitative : Comparative() {

    /**
     *
     */
    override val semanticType = SemanticType.ComparativeLimitative
  }

  /**
   *
   */
  class Accrescitive : Comparative() {

    /**
     *
     */
    override val semanticType = SemanticType.ComparativeAccrescitive
  }

  /**
   *
   */
  sealed class Equal : Comparative() {

    /**
     *
     */
    class Base : Equal() {

      /**
       *
       */
      override val semanticType = SemanticType.ComparativeEqual
    }

    /**
     *
     */
    class Negation : Equal() {

      /**
       *
       */
      override val semanticType = SemanticType.ComparativeEqualNegation
    }
  }
}