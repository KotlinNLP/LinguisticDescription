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
 * The 'predicative complement' dependency.
 *
 * @property direction the direction of the dependency, related to the governor
 */
sealed class PredCompl(override val direction: SyntacticDependency.Direction)
  : SyntacticDependency<SyntacticType>, VerbalCoreArgument {

  /**
   *
   */
  class Base(direction: SyntacticDependency.Direction) : PredCompl(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.PredCompl
  }

  /**
   *
   */
  class Interrogative(direction: SyntacticDependency.Direction) : PredCompl(direction) {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntacticType = SyntacticType.PredComplInterr
  }

  /**
   *
   */
  sealed class Subj(direction: SyntacticDependency.Direction) : PredCompl(direction) {

    /**
     *
     */
    class Base(direction: SyntacticDependency.Direction) : Subj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntacticType = SyntacticType.PredComplSubj
    }

    /**
     *
     */
    class Interrogative(direction: SyntacticDependency.Direction) : Subj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntacticType = SyntacticType.PredComplSubjInterr
    }
  }

  /**
   *
   */
  sealed class Obj(direction: SyntacticDependency.Direction) : PredCompl(direction) {

    /**
     *
     */
    class Base(direction: SyntacticDependency.Direction) : Obj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntacticType = SyntacticType.PredComplObj
    }

    /**
     *
     */
    class Interrogative(direction: SyntacticDependency.Direction) : Obj(direction) {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntacticType = SyntacticType.PredComplObjInterr
    }
  }
}
