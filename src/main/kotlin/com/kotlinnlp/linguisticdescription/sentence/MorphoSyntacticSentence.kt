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
import com.kotlinnlp.linguisticdescription.sentence.token.MutableMorphoSyntacticToken

/**
 * A sentence with morphological and syntactic information.
 *
 * @property id the sentence id
 * @property confidence the confidence score
 * @property dateTimes the list of date-times contained in this sentence (can be null)
 * @property entities the list of entities contained in this sentence (can be null)
 */
data class MorphoSyntacticSentence(
  val id: Int,
  val confidence: Double,
  val dateTimes: List<DateTime>?,
  val entities: List<Entity>?
) : SentenceIdentificable<MutableMorphoSyntacticToken>() {

  companion object {

    /**
     * Build a [MorphoSyntacticSentence] with the given properties already assigned.
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
      tokens: List<MutableMorphoSyntacticToken>,
      dateTimes: List<DateTime>?,
      entities: List<Entity>?
    ): MorphoSyntacticSentence {

      val sentence =
        MorphoSyntacticSentence(id = id, confidence = confidence, dateTimes = dateTimes, entities = entities)

      sentence._tokens.addAll(tokens)

      return sentence
    }
  }

  /**
   * The list of tokens of this sentence.
   */
  override val tokens: List<MutableMorphoSyntacticToken> get() = this._tokens

  /**
   * The mutable list of tokens of this sentence.
   */
  private val _tokens: MutableList<MutableMorphoSyntacticToken> = mutableListOf()

  /**
   * Add a new token at a given index.
   *
   * @param index the index of the [tokens] list at which to insert the token
   * @param token the token to add
   */
  fun addToken(index: Int, token: MutableMorphoSyntacticToken) {
    this._tokens.add(index, token)
  }

  /**
   * Get a list of tokens with a given governor.
   *
   * @param governorId the governor id
   *
   * @return a list of dependents tokens
   */
  fun getDependents(governorId: Int): List<MutableMorphoSyntacticToken> = this.tokens.filter {
    it.dependencyRelation.governor == governorId
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

    val self = this@MorphoSyntacticSentence

    obj(
      "id" to self.id,
      "analysisConfidence" to self.confidence,
      "entities" to self.entities?.let { array(it.map { it.toJSON() }) },
      "dateTimes" to self.dateTimes?.let { array(it.map { it.toJSON() }) },
      "tokens" to array(self.tokens.map { it.toJSON() })
    )
  }
}
