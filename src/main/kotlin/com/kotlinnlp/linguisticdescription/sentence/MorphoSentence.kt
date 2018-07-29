/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.DateTime
import com.kotlinnlp.linguisticdescription.sentence.properties.MultiWords
import com.kotlinnlp.linguisticdescription.sentence.token.MorphoToken

/**
 * A sentence with morphological and syntactic information.
 *
 * @property tokens the list of tokens of this sentence
 * @property dateTimes the list of date-time expressions contained in this sentence (can be null)
 * @property multiWords the list of multi-words expressions contained in this sentence (can be null)
 */
data class MorphoSentence(
  override val tokens: List<MorphoToken>,
  val dateTimes: List<DateTime>?,
  val multiWords: List<MultiWords>?
) : Sentence<MorphoToken> {

  /**
   * @return a string representation of this sentence
   */
  override fun toString(): String = """
    %-11s : %s
    %-11s : %s
    %-11s : %s
  """.trimIndent().format(
    "date-times", this.dateTimes?.joinToString(separator = ", ") ?: "None",
    "multi-words", this.multiWords?.joinToString(separator = ", ") ?: "None",
    "tokens", "\n\n" + this.tokens.joinToString(separator = "\n\n") { it.toString() }
  )
}
