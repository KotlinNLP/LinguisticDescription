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
sealed class SpaceTime {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  sealed class Base : SpaceTime() {

    /**
     *
     */
    override val semanticType = SemanticType.SpaceTime
  }

  /**
   *
   */
  sealed class Interval : SpaceTime() {

    /**
     *
     */
    override val semanticType = SemanticType.SpaceTimeInterval
  }

  /**
   *
   */
  sealed class Start : SpaceTime() {

    /**
     *
     */
    override val semanticType = SemanticType.SpaceTimeStart
  }

  /**
   *
   */
  sealed class End : SpaceTime() {

    /**
     *
     */
    override val semanticType = SemanticType.SpaceTimeEnd
  }
}