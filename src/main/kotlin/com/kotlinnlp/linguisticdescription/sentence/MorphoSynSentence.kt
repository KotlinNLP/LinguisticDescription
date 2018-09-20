/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.DateTime
import com.kotlinnlp.linguisticdescription.sentence.properties.Entity
import com.kotlinnlp.linguisticdescription.sentence.token.MorphoSynToken

/**
 * A sentence with morphological and syntactic information.
 *
 * @property id the sentence id
 */
data class MorphoSynSentence(val id: Int) : SentenceIdentificable<MorphoSynToken>() {

  companion object {

    /**
     * Build a [MorphoSynSentence] with the given properties already assigned.
     *
     * @param id the id of the token, unique within its sentence
     * @param confidence the confidence score
     * @param tokens the list of tokens of this sentence
     * @param dateTimes the list of date-times contained in this sentence (can be null)
     * @param entities the list of entities contained in this sentence (can be null)
     *
     * @return a new sentence with the given properties
     */
    operator fun invoke(
      id: Int,
      confidence: Double,
      tokens: List<MorphoSynToken>,
      dateTimes: List<DateTime>?,
      entities: List<Entity>?
    ): MorphoSynSentence {

      val sentence = MorphoSynSentence(id)

      sentence._confidence = confidence
      sentence._tokens.addAll(tokens)
      dateTimes?.let { sentence._dateTimes = it.toMutableList() }
      entities?.let { sentence._entities = it.toMutableList() }

      return sentence
    }
  }

  /**
   * The list of tokens of this sentence.
   */
  override val tokens: List<MorphoSynToken> get() = this._tokens

  /**
   * The mutable list of tokens of this sentence.
   */
  private val _tokens: MutableList<MorphoSynToken> = mutableListOf()

  /**
   * The confidence score.
   */
  val confidence: Double get() = this._confidence

  /**
   * The list of date-times contained in this sentence (can be null).
   */
  val dateTimes: List<DateTime>? get() = if (this::_dateTimes.isInitialized) this._dateTimes else null

  /**
   * The list of entities contained in this sentence (can be null).
   */
  val entities: List<Entity>? get() = if (this::_entities.isInitialized) this._entities else null

  /**
   * The mutable confidence score.
   */
  private var _confidence: Double = 0.0

  /**
   * The mutable list of date-times contained in this sentence.
   */
  private lateinit var _dateTimes: MutableList<DateTime>

  /**
   * The mutable list of entities contained in this sentence.
   */
  private lateinit var _entities: MutableList<Entity>

  /**
   * Update the [confidence] replacing it with a given one.
   *
   * @param confidence the confidence with which to replace the current one
   */
  fun updateConfidence(confidence: Double) {
    this._confidence = confidence
  }

  /**
   * Add a new token at a given index.
   *
   * @param index the index of the [tokens] list at which to insert the token
   * @param token the token to add
   */
  fun addToken(index: Int, token: MorphoSynToken) {

    this._tokens.add(index, token)

    this.reIndexTokens()
  }

  /**
   * Add a new date-time.
   *
   * @param dateTime the date-time to add
   */
  fun addDateTime(dateTime: DateTime) {

    if (!this::_dateTimes.isInitialized) this._dateTimes = mutableListOf()

    this._dateTimes.add(dateTime)
  }

  /**
   * Add a new entity.
   *
   * @param entity the entity to add
   */
  fun addEntity(entity: Entity) {

    if (!this::_entities.isInitialized) this._entities = mutableListOf()

    this._entities.add(entity)
  }

  /**
   * Get a list of tokens with a given governor.
   *
   * @param governorId the governor id
   *
   * @return a list of dependents tokens
   */
  fun getDependents(governorId: Int): List<MorphoSynToken.Single> = this.tokens.flatMap {
    when(it) {
      is MorphoSynToken.Single -> if (it._syntacticRelation.governor == governorId) listOf(it) else listOf()
      is MorphoSynToken.Composite -> it.components.filter { it.syntacticRelation.governor == governorId }
    }
  }

  /**
   * @return a string representation of this sentence
   */
  override fun toString(): String = """
    %-10s : %d
    %-10s : %.2f%%
    %-10s : %s
    %-10s : %s
    %-10s : %s
  """.trimIndent().format(
    "id", this.id,
    "confidence", 100.0 * this.confidence,
    "date-times", this.dateTimes?.joinToString(separator = ", ") ?: "None",
    "entities", this.entities?.joinToString(separator = ", ") ?: "None",
    "tokens", "\n\n" + this.tokens.joinToString(separator = "\n\n") { it.toString(prefix = "\t") }
  )

  /**
   * @return the JSON array (of Token objects) that represents this sentence
   */
  fun toJSON(): JsonObject = json {

    val self = this@MorphoSynSentence

    obj(
      "id" to self.id,
      "analysisConfidence" to self.confidence,
      "entities" to self.entities?.let { array(it.map { it.toJSON() }) },
      "dateTimes" to self.dateTimes?.let { array(it.map { it.toJSON() }) },
      "tokens" to array(self.tokens.map { it.toJSON() })
    )
  }
}
