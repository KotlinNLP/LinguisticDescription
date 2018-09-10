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
 * The 'subject' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class Subject(override val direction: SyntaxDependency.Direction)
  : SyntaxDependency<SyntaxType>, VerbalCoreArgument {

  /**
   *
   */
  class Base(direction: SyntaxDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.Subject
  }

  /**
   *
   */
  class Passive(direction: SyntaxDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.PassiveSubject
  }

  /**
   *
   */
  class Interrogative(direction: SyntaxDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.InterrogativeSubject
  }

  /**
   *
   */
  class Extra(direction: SyntaxDependency.Direction) : Subject(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.ExtraSubject
  }
}
