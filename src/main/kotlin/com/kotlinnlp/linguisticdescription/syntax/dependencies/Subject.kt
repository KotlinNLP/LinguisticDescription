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
 * The 'subject' dependency.
 *
 * @property type the type of this dependency
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Subject(type: SyntacticType, direction: SyntacticDependency.Direction)
  : VerbalCoreArgument, SyntacticDependency.Base(type = type, direction = direction) {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction)
    : Subject(type = SyntacticType.Subject, direction = direction)

  /**
   *
   */
  class Passive(direction: SyntacticDependency.Direction)
    : Subject(type = SyntacticType.PassiveSubject, direction = direction)

  /**
   *
   */
  class Interrogative(direction: SyntacticDependency.Direction)
    : Subject(type = SyntacticType.InterrogativeSubject, direction = direction)

  /**
   *
   */
  class Extra(direction: SyntacticDependency.Direction)
    : Subject(type = SyntacticType.ExtraSubject, direction = direction)
}
