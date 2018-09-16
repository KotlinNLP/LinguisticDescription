/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.morphology.ScoredMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SyntacticRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation

/**
 * A [MorphoSyntacticToken] with mutable properties.
 *
 * @property id the id of the token, unique within its sentence
 */
@Suppress("PropertyName")
abstract class MutableMorphoSyntacticToken(override val id: Int) : MorphoSyntacticToken {

  /**
   * The list of scored morphologies, sorted by descending score.
   */
  override val morphologies: List<ScoredMorphology> get() = this._morphologies

  /**
   * The dependency relation with its governor.
   */
  override val syntacticRelation: SyntacticRelation get() = this._syntacticRelation

  /**
   * The list of co-references (can be null).
   */
  override val coReferences: List<CoReference>? get() =
    if (this::_coReferences.isInitialized) this._coReferences else null

  /**
   * The list of semantic relations (can be null).
   */
  override val semanticRelations: List<SemanticRelation>? get() =
    if (this::_semanticRelations.isInitialized) this._semanticRelations else null

  /**
   * The mutable list of scored morphologies, sorted by descending score.
   */
  internal val _morphologies: MutableList<ScoredMorphology> = mutableListOf()

  /**
   * The variable syntactic relation with the governor.
   */
  internal lateinit var _syntacticRelation: SyntacticRelation

  /**
   * The mutable list of co-references.
   */
  internal lateinit var _coReferences: MutableList<CoReference>

  /**
   * The mutable list of semantic relations.
   */
  internal lateinit var _semanticRelations: MutableList<SemanticRelation>

  /**
   * Add a new morphology.
   *
   * @param morphology the morphology to add
   */
  fun addMorphology(morphology: ScoredMorphology) {
    this._morphologies.add(morphology)
  }

  /**
   * Remove the morphology at a given index.
   *
   * @param index an index of a morphology in [morphologies]
   */
  fun removeMorphology(index: Int) {
    this._morphologies.removeAt(index)
  }

  /**
   * Update the [syntacticRelation] replacing it with a given one.
   *
   * @param syntacticRelation the syntactic relation with which to replace the current one
   */
  fun updateSyntacticRelation(syntacticRelation: SyntacticRelation) {
    this._syntacticRelation = syntacticRelation
  }

  /**
   * Add a new co-reference.
   *
   * @param coReference the co-reference to add
   */
  fun addCoReference(coReference: CoReference) {

    if (!this::_coReferences.isInitialized) this._coReferences = mutableListOf()

    this._coReferences.add(coReference)
  }

  /**
   * Remove the co-reference at a given index.
   * It is required that the [coReferences] list has been set.
   *
   * @param index the index of a co-reference in [coReferences]
   */
  fun removeCoReference(index: Int) {
    this._coReferences.removeAt(index)
  }

  /**
   * Add a new semantic relation.
   *
   * @param semanticRelation the semantic relation to add
   */
  fun addSemanticRelation(semanticRelation: SemanticRelation) {

    if (!this::_semanticRelations.isInitialized) this._semanticRelations = mutableListOf()

    this._semanticRelations.add(semanticRelation)
  }

  /**
   * Remove the semantic relation at a given index.
   * It is required that the [semanticRelations] list has been set.
   *
   * @param index the index of a semantic relation in [semanticRelations]
   */
  fun removeSemanticRelation(index: Int) {
    this._semanticRelations.removeAt(index)
  }
}
