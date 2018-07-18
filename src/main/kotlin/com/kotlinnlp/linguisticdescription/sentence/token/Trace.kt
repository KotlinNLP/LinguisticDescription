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
 * A trace token.
 *
 * @property id the id of the token, unique within its sentence
 * @property morphologies the list of scored morphologies, sorted by descending score
 * @property dependencyRelation the dependency relation with its governor
 * @property coReferences the list of co-references (can be null)
 * @property semanticRelations the list of semantic relations (can be null)
 * @property positionedAfter the id of the token after which this trace is positioned
 */
data class Trace(
  override val id: Int,
  override val morphologies: List<ScoredMorphology>,
  override val dependencyRelation: DependencyRelation,
  override val coReferences: List<CoReference>?,
  val semanticRelations: List<SemanticRelation>?,
  val positionedAfter: Int
) : MorphoSyntacticToken {

  /**
   * @return a string representation of this token
   */
  override fun toString(): String = """
    [%d] TRACE
        dependency: %s
        co-references: %s
        semantic relations: %s
  """.trimIndent().format(
    this.id,
    this.dependencyRelation,
    this.coReferences?.joinToString(separator = ", ") ?: "None",
    this.semanticRelations?.joinToString(separator = ", ") ?: "None"
  )
}
