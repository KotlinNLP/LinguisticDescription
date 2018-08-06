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
 */
sealed class PredCompl : SyntaxDependency, VerbalCoreArgument {

  /**
   *
   */
  class Base : PredCompl() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.PredCompl
  }

  /**
   *
   */
  class Interrogative : PredCompl() {

    /**
     * The type associated to this dependency.
     */
    override val type: SyntaxType = SyntaxType.PredComplInterr
  }

  /**
   *
   */
  sealed class Subj : PredCompl() {

    /**
     *
     */
    class Base : Subj() {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplSubj
    }

    /**
     *
     */
    class Interrogative : Subj() {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplSubjInterr
    }
  }

  /**
   *
   */
  sealed class Obj : PredCompl() {

    /**
     *
     */
    class Base : Obj() {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplObj
    }

    /**
     *
     */
    class Interrogative : Obj() {

      /**
       * The type associated to this dependency.
       */
      override val type: SyntaxType = SyntaxType.PredComplObjInterr
    }
  }
}