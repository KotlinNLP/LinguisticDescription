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
 * The 'auxiliary' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Auxiliary(override val direction: SyntacticDependency.Direction) : SyntacticDependency<SyntacticType> {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction) : Auxiliary(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.Aux
  }

  /**
   *
   */
  class Tense(direction: SyntacticDependency.Direction) : Auxiliary(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.AuxTense
  }

  /**
   *
   */
  class Passive(direction: SyntacticDependency.Direction) : Auxiliary(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.AuxPassive
  }

  /**
   *
   */
  class Progressive(direction: SyntacticDependency.Direction) : Auxiliary(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.AuxProgressive
  }
}
