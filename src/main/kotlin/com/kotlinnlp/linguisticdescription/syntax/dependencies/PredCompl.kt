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
 * The 'predicative complement' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class PredCompl(override val direction: SyntaxDependency.Direction)
  : SyntaxDependency<SyntaxType>, VerbalCoreArgument {

  /**
   *
   */
  class Base(direction: SyntaxDependency.Direction) : PredCompl(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.PredCompl
  }

  /**
   *
   */
  class Interrogative(direction: SyntaxDependency.Direction) : PredCompl(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.PredComplInterr
  }

  /**
   *
   */
  sealed class Subj(direction: SyntaxDependency.Direction) : PredCompl(direction) {

    /**
     *
     */
    class Base(direction: SyntaxDependency.Direction) : Subj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplSubj
    }

    /**
     *
     */
    class Interrogative(direction: SyntaxDependency.Direction) : Subj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplSubjInterr
    }
  }

  /**
   *
   */
  sealed class Obj(direction: SyntaxDependency.Direction) : PredCompl(direction) {

    /**
     *
     */
    class Base(direction: SyntaxDependency.Direction) : Obj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplObj
    }

    /**
     *
     */
    class Interrogative(direction: SyntaxDependency.Direction) : Obj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplObjInterr
    }
  }
}
