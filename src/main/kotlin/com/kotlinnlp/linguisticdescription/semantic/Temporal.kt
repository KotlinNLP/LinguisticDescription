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
sealed class Temporal {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  class Base : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.Temporal
  }

  /**
   *
   */
  class Start : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.TemporalStart
  }

  /**
   *
   */
  class End : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.TemporalEnd
  }

  /**
   *
   */
  class Prev : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.TemporalPrev
  }

  /**
   *
   */
  class Post : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.TemporalPost
  }

  /**
   *
   */
  class Proximity : Temporal() {

    /**
     *
     */
    override val semanticType = SemanticType.TemporalProximity
  }
}