/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.POSTag
import com.kotlinnlp.linguisticdescription.morphology.ScoredSingleMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SyntacticRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation

/**
 * A word-trace token.
 *
 * @property id the id of the token, unique within its sentence
 * @property form the form of the token
 */
class WordTrace(
  override val id: Int,
  override val form: String
) : FormToken, MorphoSynToken.Single() {

  companion object {

    /**
     * Build a [WordTrace] with the given properties already assigned.
     *
     * @param id the id of the token, unique within its sentence
     * @param form the form of the token
     * @param pos the Part-Of-Speech
     * @param morphologies the list of scored single morphologies, sorted by descending score
     * @param contextMorphologies the list of scored single morphologies of context, sorted by descending score
     * @param syntacticRelation the syntactic relation with the governor
     * @param coReferences the list of co-references (can be null)
     * @param semanticRelations the list of semantic relations (can be null)
     *
     * @return a new token with the given properties
     */
    operator fun invoke(
      id: Int,
      form: String,
      pos: POSTag?,
      morphologies: List<ScoredSingleMorphology>,
      contextMorphologies: List<ScoredSingleMorphology>,
      syntacticRelation: SyntacticRelation,
      coReferences: List<CoReference>?,
      semanticRelations: List<SemanticRelation>?
    ): WordTrace {

      val token = WordTrace(id = id, form = form)

      token._pos = pos
      token._morphologies.addAll(morphologies)
      token._contextMorphologies.addAll(contextMorphologies)
      token._syntacticRelation = syntacticRelation
      coReferences?.let { token._coReferences = it.toMutableList() }
      semanticRelations?.let { token._semanticRelations = it.toMutableList() }

      return token
    }
  }

  /**
   * The label that defines the type of this token.
   */
  override val type: Type = Type.WordTrace

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s' (TRACE)
        dependency: %d
        co-references: %s
        semantic relations: %s
  """.trimIndent().format(
    this.id,
    this.form,
    this.syntacticRelation,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
