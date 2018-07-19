/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.sentence.multiwords.datetime.DateTime
import com.kotlinnlp.linguisticdescription.sentence.multiwords.Entity
import com.kotlinnlp.linguisticdescription.sentence.token.MorphoSyntacticToken

/**
 * A sentence with morphological and syntactic information.
 *
 * @property id the sentence id
 * @property confidence the confidence score
 * @property tokens the list of tokens of this sentence
 * @property dateTimes the list of date-times contained in this sentence (can be null)
 * @property entities the list of entities contained in this sentence (can be null)
 */
data class MorphoSyntacticSentence(
  val id: Int,
  val confidence: Double,
  override val tokens: List<MorphoSyntacticToken>,
  val dateTimes: List<DateTime>? = null,
  val entities: List<Entity>? = null
) : Sentence<MorphoSyntacticToken> {

  /**
   * The tokens of this sentence associated by ID.
   */
  private val tokensByID: Map<Int, MorphoSyntacticToken> = this.tokens.associateBy { it.id }

  /**
   * @param id the id of a token of this sentence
   *
   * @throws NoSuchElementException when the given [id] is not within the token ids of this sentence
   *
   * @return the token of this sentence with the given id
   */
  fun getTokenByID(id: Int): MorphoSyntacticToken = this.tokensByID.getValue(id)

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
    "datetimes", this.dateTimes?.joinToString(separator = ", ") ?: "None",
    "entities", this.entities?.joinToString(separator = ", ") ?: "None",
    "tokens", "\n\n" + this.tokens.joinToString(separator = "\n\n") { it.toString(prefix = "\t") }
  )

  /**
   * @return the JSON array (of Token objects) that represents this sentence
   */
  fun toJSON(): JsonArray<JsonObject> = JsonArray(this.tokens.map { it.toJSON() })
}
