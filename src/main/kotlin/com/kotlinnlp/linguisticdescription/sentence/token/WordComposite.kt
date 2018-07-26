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
 * A word token that is composed by the union of more words.
 *
 * @property id the id of the token, unique within its sentence
 * @property form the form of the token
 * @property position the position of the token
 * @property morphologies the list of scored morphologies, sorted by descending score
 * @property dependencyRelation the dependency relation with its governor
 * @property coReferences the list of co-references (can be null)
 * @property semanticRelations the list of semantic relations (can be null)
 * @property components the list of tokens that compose this word
 */
class WordComposite(
  id: Int,
  form: String,
  position: Position,
  morphologies: List<ScoredMorphology>,
  dependencyRelation: DependencyRelation,
  coReferences: List<CoReference>?,
  semanticRelations: List<SemanticRelation>?,
  val components: List<Word>
) : Word(
  id = id,
  form = form,
  position = position,
  morphologies = morphologies,
  dependencyRelation = dependencyRelation,
  coReferences = coReferences,
  semanticRelations = semanticRelations
) {

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s'
        %s
        components: %s
        dependency: %s
        co-references: %s
        semantic relations: %s
  """.trimIndent().format(
    this.id,
    this.form,
    this.morphologies.joinToString(" | ") { it.list.joinToString(" ") { it.type.annotation  } },
    this.components.joinToString(" + ") { it.form },
    this.dependencyRelation,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
