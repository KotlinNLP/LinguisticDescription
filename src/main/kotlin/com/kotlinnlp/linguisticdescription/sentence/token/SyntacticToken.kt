/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SyntacticRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation

/**
 * A syntactic token.
 */
interface SyntacticToken : TokenIdentificable {

  /**
   * The syntactic relation.
   */
  val syntacticRelation: SyntacticRelation

  /**
   * The list of co-references (can be null).
   */
  val coReferences: List<CoReference>?

  /**
   * The list of semantic relations (can be null).
   */
  val semanticRelations: List<SemanticRelation>?
}
