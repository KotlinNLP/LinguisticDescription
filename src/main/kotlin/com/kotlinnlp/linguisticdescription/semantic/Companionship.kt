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
sealed class Companionship {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  class Base : Companionship() {

    /**
     *
     */
    override val semanticType = SemanticType.Companionship
  }

  /**
   *
   */
  class Figurative : Companionship() {

    /**
     *
     */
    override val semanticType = SemanticType.CompanionshipFigurative
  }
}