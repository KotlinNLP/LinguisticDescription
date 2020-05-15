/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.paragraph

import com.kotlinnlp.linguisticdescription.sentence.RealSentence
import com.kotlinnlp.linguisticdescription.sentence.flattenTokens
import com.kotlinnlp.linguisticdescription.sentence.token.RealToken
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Position
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Positionable
import com.kotlinnlp.utils.forEachIndicesRange

/**
 * A paragraph of a text, as sequence of sentences.
 *
 * @property sentences the list of sentences that compose this paragraph
 * @param index the index of this paragraph (default 0)
 */
open class Paragraph<TokenType: RealToken, SentenceType: RealSentence<TokenType>>(
  val sentences: List<SentenceType>,
  index: Int = 0
) : Positionable {

  /**
   * The position of this paragraph within a text.
   */
  override val position = Position(
    index = index,
    start = this.sentences.first().position.start,
    end = this.sentences.last().position.end)

  /**
   * All the tokens in a flat list.
   */
  val flattenTokens: List<TokenType> by lazy { this.sentences.flattenTokens() }

  /**
   * Incremental sums of the sentences lengths.
   */
  private val tokensSums: List<Int> = this.sentences
    .takeLast(this.sentences.size - 1)
    .fold(mutableListOf(this.sentences.first().tokens.size)) { tokensSum, sentence ->
      tokensSum.apply {
        add(last() + sentence.tokens.size)
      }
    }

  /**
   * Iterate all the possible spans of this paragraph.
   * Each span is a tokens sequence with a size between 1 and [maxSpanLength].
   *
   * @param maxSpanLength the max span length
   * @param action the callback called for each span, passing it the indices range of the span tokens
   */
  fun forEachSpan(maxSpanLength: Int, action: (IntRange) -> Unit) =
    this.sentences.zip(this.tokensSums).forEach { (sentence, tokensSum) ->

      val tokensOffset = tokensSum - sentence.tokens.size

      sentence.tokens.forEachIndicesRange(min = 1, max = maxSpanLength) { span ->
        action(IntRange(start = tokensOffset + span.first, endInclusive = tokensOffset + span.last))
      }
    }

  /**
   * Map all the possible spans of this paragraph with a transform function.
   * Each span is a tokens sequence with a size between 1 and [maxSpanLength].
   *
   * @param maxSpanLength the max span length
   * @param transform the transform function called for each span, passing it the indices range of the span tokens
   */
  fun <R> mapSpans(maxSpanLength: Int, transform: (IntRange) -> R): List<R> {

    val acc = mutableListOf<R>()

    this.forEachSpan(maxSpanLength) { acc.add(transform(it)) }

    return acc.toList()
  }
}
