/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.POSTag
import com.kotlinnlp.linguisticdescription.morphology.ScoredMorphology
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.sentence.token.properties.CoReference
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SyntacticRelation
import com.kotlinnlp.linguisticdescription.sentence.token.properties.SemanticRelation
import com.kotlinnlp.linguisticdescription.syntax.SyntacticDependency
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * A with morphological and syntactic properties.
 *
 * @property id the id of the token, unique within its sentence
 */
@Suppress("PropertyName")
abstract class MorphoSynToken(override val id: Int) : ScoredMorphoToken, SyntacticToken {

  /**
   * The label that defines the type of this token.
   */
  abstract val type: String

  /**
   * The Part-Of-Speech.
   */
  override val pos: POSTag? get() = this._pos

  /**
   * The list of scored morphologies, sorted by descending score.
   */
  override val morphologies: List<ScoredMorphology> get() = this._morphologies

  /**
   * The list of single morphologies, eventually flatting the components morphologies.
   * This property should be accessed only if this token contains only single morphologies.
   */
  val flatMorphologies: List<SingleMorphology> get() =
    (this as? WordComposite)?.components?.flatMap { it._morphologies.map { it.components.single() } }
      ?: this._morphologies.map { it.components.single() }

  /**
   * The syntactic relation.
   */
  override val syntacticRelation: SyntacticRelation get() = this._syntacticRelation

  /**
   * The list of syntactic relations. It contains more than one element if this is a [WordComposite] token.
   */
  val flatSyntacticRelations: List<SyntacticRelation> get() =
    (this as? WordComposite)?.components?.map { it._syntacticRelation } ?: listOf(this._syntacticRelation)

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
   * The variable Part-Of-Speech.
   */
  internal var _pos: POSTag? = null

  /**
   * The mutable list of scored morphologies, sorted by descending score.
   */
  internal val _morphologies: MutableList<ScoredMorphology> = mutableListOf()

  /**
   * The variable syntactic relation.
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
   * Update the [pos] replacing it with a given one.
   *
   * @param pos the POS with which to replace the current one
   */
  fun updatePOS(pos: POSTag) {
    this._pos = pos
  }

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

  /**
   * @return the JSON object that represents this token
   */
  fun toJSON(): JsonObject {

    val jsonObject = json {

      val self = this@MorphoSynToken

      obj(
        "id" to self.id,
        "type" to self.type,
        "pos" to self.pos?.toString(),
        "dependency" to self.syntacticRelation.toJSON(),
        "coReferences" to self.coReferences?.let { array(it.map { it.toJSON() }) },
        "semanticRelations" to self.semanticRelations?.let { array(it.map { it.toString() }) },
        "morphology" to if (self.morphologies.isNotEmpty()) array(self.morphologies.map { it.toJSON() }) else null
      )
    }

    if (this is RealToken) {
      jsonObject["surface"] = json {
        obj(
          "form" to this@MorphoSynToken.form,
          "translitForm" to this@MorphoSynToken.form // TODO: set it properly
        )
      }
      jsonObject["position"] = this.position.toJSON()
    }

    return jsonObject
  }

  /**
   * @param syntaxClass the [KClass] of a [SyntacticDependency]
   *
   * @return true if this token is a dependent of the given type, otherwise false
   */
  fun <T : SyntacticDependency.Base>isDependentAs(syntaxClass: KClass<T>): Boolean = if (this is WordComposite)
    this.components.any { it.syntacticRelation.dependency::class.isSubclassOf(syntaxClass) }
  else
    this.syntacticRelation.dependency::class.isSubclassOf(syntaxClass)

  /**
   * @param token another morpho-syn token
   *
   * @return true if this token matches at least one morphology of the given one, otherwise false
   */
  fun agreeMorphology(token: MorphoSynToken): Boolean {
    val tokenMorphologies: List<SingleMorphology> = token.flatMorphologies
    return this.flatMorphologies.any { tokenMorphologies.any { tMorpho -> tMorpho.agree(it) } }
  }
}
