/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.DependencyRelation

/**
 * A syntactic token.
 */
interface SyntacticToken : TokenIdentificable {

  /**
   * The dependency relation with its governor.
   */
  val dependencyRelation: DependencyRelation

  /**
   * The list of co-references (can be null).
   */
  val coReferences: List<CoReference>?

  /**
   * @param prefix a prefix to prepend to each line that composes the string representation of this token
   *
   * @return a prefixed string representation of this token
   */
  fun toString(prefix: String): String = prefix + this.toString().replace("\n", "\n" + prefix)
}
