/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.sentence.token.MorphoSynToken
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Position
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Positionable

/**
 * A sentence with morphological and syntactic information.
 *
 * @property id the sentence id
 * @property confidence the confidence score given by the system to the analysis of this sentence
 * @property position the position of the sentence in the text
 */
data class MorphoSynSentence(
  val id: Int,
  var confidence: Double = 0.0
) : RealSentence<MorphoSynToken>, SentenceIdentificable<MorphoSynToken>() {

  companion object {

    /**
     * Build a [MorphoSynSentence] with a given list of tokens.
     *
     * @param id the id of the token, unique within its sentence
     * @param confidence the confidence score
     * @param tokens the list of tokens of this sentence
     *
     * @return a new sentence with the given properties
     */
    operator fun invoke(id: Int, confidence: Double, tokens: List<MorphoSynToken>): MorphoSynSentence =
      MorphoSynSentence(id = id, confidence = confidence)
        .apply { tokens.forEach { this.addToken(it) } }

    /**
     * Build a [MorphoSynSentence] with a given list of tokens and a position.
     *
     * @param id the id of the token, unique within its sentence
     * @param confidence the confidence score
     * @param tokens the list of tokens of this sentence
     *
     * @return a new sentence with the given properties
     */
    operator fun invoke(id: Int,
                        confidence: Double,
                        tokens: List<MorphoSynToken>,
                        position: Position): MorphoSynSentence =
      MorphoSynSentence(id = id, confidence = confidence, tokens = tokens)
        .apply { setPosition(position) }
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
   * The position of the sentence in the text.
   */
  override val position: Position get() = this._position

  /**
   * The mutable position of the sentence.
   */
  private lateinit var _position: Position

  /**
   * Add a new token at a given index.
   *
   * @param token the token to add
   * @param index the index of the [tokens] list at which to insert the token (if null insert as last)
   */
  fun addToken(token: MorphoSynToken, index: Int? = null) {

    this._tokens.add(index ?: this._tokens.size, token)

    this.reIndexTokens()
  }

  /**
   * Get a list of tokens with a given governor.
   *
   * @param governorId the governor id
   *
   * @return a list of dependents tokens
   */
  fun getDependents(governorId: Int): List<MorphoSynToken.Single> = this.tokens.flatMap { tk ->
    when (tk) {
      is MorphoSynToken.Single -> if (tk.syntacticRelation.governor == governorId) listOf(tk) else listOf()
      is MorphoSynToken.Composite -> tk.components.filter { it.syntacticRelation.governor == governorId }
    }
  }

  /**
   * Set the position of this sentence.
   */
  fun setPosition(position: Position) {
    this._position = position
  }

  /**
   * Set the position of this sentence automatically, based on the position of its tokens.
   */
  fun autoSetPosition() {

    this._position = Position(
      index = if (this::_position.isInitialized) this._position.index else 0,
      start = this._tokens.asSequence().mapNotNull { it as? Positionable }.firstOrNull()?.position?.start ?: 0,
      end = this._tokens.asSequence().mapNotNull { it as? Positionable }.lastOrNull()?.position?.end ?: 0
    )
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
    "tokens", "\n\n" + this.tokens.joinToString(separator = "\n\n") { it.toString(prefix = "\t") }
  )

  /**
   * Note: the property [position] must be set to use this method.
   *
   * @return the JSON object that represents this sentence
   */
  fun toJSON(): JsonObject = json {

    val self = this@MorphoSynSentence

    require(self::_position.isInitialized) {
      "The JSON representation of a sentence cannot be get if the property 'position' is not set (the methods " +
        "'setPosition' and 'autoSetPosition' can be used for this purpose)."
    }

    obj(
      "id" to self.id,
      "score" to self.confidence,
      "position" to self.position.toJSON(),
      "tokens" to array(self.tokens.map { it.toJSON() })
    )
  }
}
