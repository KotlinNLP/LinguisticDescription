/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.POSTag
import com.kotlinnlp.linguisticdescription.morphology.ScoredMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.*

/**
 * A word token.
 *
 * @property id the id of the token, unique within its sentence
 * @property form the form of the token
 * @property position the position of the token
 */
open class Word(
  override val id: Int,
  override val form: String,
  override val position: Position
) : RealToken, MorphoSynToken.Single() {

  companion object {

    /**
     * Build a [Word] with the given properties already assigned.
     *
     * @param id the id of the token, unique within its sentence
     * @param form the form of the token
     * @param position the position of the token
     * @param pos the Part-Of-Speech
     * @param morphologies the list of scored morphologies, sorted by descending score
     * @param syntacticRelation the syntactic relation with the governor
     * @param coReferences the list of co-references (can be null)
     * @param semanticRelations the list of semantic relations (can be null)
     *
     * @return a new token with the given properties
     */
    operator fun invoke(
      id: Int,
      form: String,
      position: Position,
      pos: POSTag?,
      morphologies: List<ScoredMorphology>,
      syntacticRelation: SyntacticRelation,
      coReferences: List<CoReference>?,
      semanticRelations: List<SemanticRelation>?
    ): Word {

      val token = Word(id = id, form = form, position = position)

      token._pos = pos
      token._morphologies.addAll(morphologies)
      token._syntacticRelation = syntacticRelation
      coReferences?.let { token._coReferences = it.toMutableList() }
      semanticRelations?.let { token._semanticRelations = it.toMutableList() }

      return token
    }
  }

  /**
   * The label that defines the type of this token.
   */
  override val type: String = "WORD"

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] '%s'
        %s
        dependency: %s
        co-references: %s
        semantic relations: %s
  """.trimIndent().format(
    this.id,
    this.form,
    this.morphologies.joinToString(" | ") { it.components.joinToString(" ") { it.pos.annotation  } },
    this.syntacticRelation,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
