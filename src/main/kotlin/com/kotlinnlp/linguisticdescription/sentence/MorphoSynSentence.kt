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

/**
 * A sentence with morphological and syntactic information.
 *
 * @property id the sentence id
 * @property confidence the confidence score given by the system to the analysis of this sentence
 */
data class MorphoSynSentence(val id: Int, var confidence: Double = 0.0) : SentenceIdentificable<MorphoSynToken>() {

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
        .apply { _tokens.addAll(tokens) }
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
   * Get a list of tokens with a given governor.
   *
   * @param governorId the governor id
   *
   * @return a list of dependents tokens
   */
  fun getDependents(governorId: Int): List<MorphoSynToken.Single> = this.tokens.flatMap { tk ->
    when (tk) {
      is MorphoSynToken.Single -> if (tk._syntacticRelation.governor == governorId) listOf(tk) else listOf()
      is MorphoSynToken.Composite -> tk.components.filter { it.syntacticRelation.governor == governorId }
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
      "tokens" to array(self.tokens.map { it.toJSON() })
    )
  }
}
