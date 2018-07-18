/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.morphology.ScoredMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.*

/**
 * A word token.
 *
 * @property id the id of the token, unique within its sentence
 * @property form the form of the token
 * @property position the position of the token
 * @property lexicalInterpretations the list of lexical interpretations of the [form]
 * @property dependencyRelation the dependency relation with its governor
 * @property coReferences the list of co-references (can be null)
 * @property semanticRelations the list of semantic relations (can be null)
 */
data class Word(
  override val id: Int,
  override val form: String,
  override val position: Position,
  override val lexicalInterpretations: List<ScoredMorphology>,
  override val dependencyRelation: DependencyRelation,
  override val coReferences: List<CoReference> = emptyList(),
  val semanticRelations: List<SemanticRelation> = emptyList()
) : RealToken, LexicalToken, SyntacticToken {

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s'
        %s
        dependency: %s
        corefs: %s
        sem-rel: %s
  """.trimIndent().format(
    this.id,
    this.form,
    this.lexicalInterpretations.joinToString(" | ") { it.list.joinToString(" ") { it.type.annotation  } },
    this.dependencyRelation,
    this.coReferences.joinToString(separator = ", "),
    this.semanticRelations.joinToString(separator = ", ")
  )
}
