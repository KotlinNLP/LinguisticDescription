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
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Subject(override val direction: SyntacticDependency.Direction)
  : SyntacticDependency<SyntacticType>, VerbalCoreArgument {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.Subject
  }

  /**
   *
   */
  class Passive(direction: SyntacticDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.PassiveSubject
  }

  /**
   *
   */
  class Interrogative(direction: SyntacticDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.InterrogativeSubject
  }

  /**
   *
   */
  class Extra(direction: SyntacticDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.ExtraSubject
  }
}
