/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Head
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Surface

/**
 * A word-trace token.
 *
 * @property id the id of the token, unique within its sentence
 * @property head the head of the token
 * @property deprel the dependency relation with the [head]
 * @property coReferences the list of co-references (can be null)
 * @property descendantsCount the amount of descendants of the token
 * @property semanticRelations the list of semantic relations (can be null)
 * @property pos the POS tag
 * @property surface the surface information
 * @property positionedAfter the id of the token after which this trace is positioned
 */
data class WordTrace(
  override val id: Int,
  override val head: Head,
  override val deprel: String,
  override val coReferences: List<CoReference>?,
  override val descendantsCount: Int,
  override val semanticRelations: List<SemanticRelation>?,
  val pos: String,
  val surface: Surface,
  val positionedAfter: Int
) : Token {

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s' (TRACE) %s %s
        head: %d
        corefs: %s
        descendants: %d
        sem-rel: %s
  """.trimIndent().format(
    this.id,
    this.surface.form,
    this.pos,
    this.deprel,
    this.head,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.descendantsCount,
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
