/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.sentence.properties.DateTime
import com.kotlinnlp.linguisticdescription.sentence.properties.Entity
import com.kotlinnlp.linguisticdescription.sentence.token.SyntacticToken

/**
 * A sentence.
 *
 * @property id the sentence id
 * @property confidence the confidence score
 * @property tokens the list of tokens of this sentence
 * @property dateTimes the list of datetimes contained in this sentence
 * @property entities the list of entities contained in this sentence
 */
data class Sentence(
  val id: Int,
  val confidence: Double,
  val tokens: List<SyntacticToken>,
  val dateTimes: List<DateTime>?,
  val entities: List<Entity>?
) {

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
}
