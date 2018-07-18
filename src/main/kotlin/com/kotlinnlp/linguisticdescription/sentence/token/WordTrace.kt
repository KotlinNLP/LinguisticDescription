/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.morphology.ScoredMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.DependencyRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation

/**
 * A word-trace token.
 *
 * @property id the id of the token, unique within its sentence
 * @property form the form of the token
 * @property lexicalInterpretations the list of lexical interpretations of the [form]
 * @property dependencyRelation the dependency relation with its governor
 * @property coReferences the list of co-references (can be null)
 * @property semanticRelations the list of semantic relations (can be null)
 * @property positionedAfter the id of the token after which this trace is positioned
 */
data class WordTrace(
  override val id: Int,
  override val form: String,
  override val lexicalInterpretations: List<ScoredMorphology>,
  override val dependencyRelation: DependencyRelation,
  override val coReferences: List<CoReference>?,
  val semanticRelations: List<SemanticRelation>?,
  val positionedAfter: Int
) : FormToken, LexicalToken, SyntacticToken {

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s' (TRACE) %s
        dependency: %d
        corefs: %s
        sem-rel: %s
  """.trimIndent().format(
    this.id,
    this.form,
    this.dependencyRelation,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
