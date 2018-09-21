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
 * @property type the type of this dependency
 * @property direction the direction of the dependency, related to the governor
 */
sealed class RestrictiveModifier(type: SyntacticType, direction: SyntacticDependency.Direction)
  : SyntacticDependency.Base(type = type, direction = direction) {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RMod, direction = direction)

  /**
   *
   */
  class Comparative(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModCompar, direction = direction)

  /**
   *
   */
  class Interrogative(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModInterr, direction = direction)

  /**
   *
   */
  class Exclamative(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModExclamative, direction = direction)

  /**
   *
   */
  class Negative(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModNeg, direction = direction)

  /**
   *
   */
  class Possessive(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModPoss, direction = direction)

  /**
   *
   */
  class Quantitative(direction: SyntacticDependency.Direction)
    : RestrictiveModifier(type = SyntacticType.RModQuant, direction = direction)
}
