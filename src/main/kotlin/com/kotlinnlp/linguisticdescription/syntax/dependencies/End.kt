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
 * The 'auxiliary' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class End(override val direction: SyntaxDependency.Direction) : SyntaxDependency<SyntaxType> {

  /**
   *
   */
  class Assertive(direction: SyntaxDependency.Direction) : End(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.EndAssertive
  }

  /**
   *
   */
  class Interrogative(direction: SyntaxDependency.Direction) : End(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.EndInterrogative
  }

  /**
   *
   */
  class Imperative(direction: SyntaxDependency.Direction) : End(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.EndImperative
  }
}
