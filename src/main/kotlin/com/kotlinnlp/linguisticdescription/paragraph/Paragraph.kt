/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.paragraph

import com.kotlinnlp.linguisticdescription.sentence.RealSentence
import com.kotlinnlp.linguisticdescription.sentence.token.RealToken
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Position
import com.kotlinnlp.utils.forEachIndicesRange

/**
 * @property sentences the list of sentences that compose this paragraph
 * @param index the index of this paragraph (default 0)
 */
open class Paragraph<TokenType: RealToken, SentenceType: RealSentence<TokenType>>(
  val sentences: List<SentenceType>,
  index: Int = 0
) : RealSentence<TokenType> {

  /**
   * The start is the position start of the first sentence.
   * The end is the position end of the last sentence.
   */
  override val position = Position(
    index = index,
    start = this.sentences.first().position.start,
    end = this.sentences.last().position.end)

  /**
   * The list of tokens.
   */
  override val tokens: List<TokenType> = this.sentences.flatMap { it.tokens }

  /**
   * Support structure to group the spans per sentence.
   */
  private val tokensSums: List<Int> = run {

    val tokensSums = MutableList(size = this.sentences.size, init = { this.sentences[it].tokens.size } )

    (1 until tokensSums.size).forEach { i ->
      tokensSums[i] += tokensSums[i - 1]
    }

    tokensSums
  }

  /**
   * @param k the max span length
   */
  fun forEachSpan(k: Int, action: (IntRange) -> Unit) =
    this.tokens
      .groupBy { tk -> this.tokensSums.first { tk.position.index < it }  }
      .entries.forEachIndexed { sentenceIndex, (tokensSum, tokensGroup) ->

      val tokensOffset = tokensSum - this.sentences[sentenceIndex].tokens.size

      tokensGroup.forEachIndicesRange(min = 1, max = k) { span ->
        action(IntRange(start = span.start + tokensOffset, endInclusive = span.endInclusive + tokensOffset))
      }
    }

  /**
   * @param k the max span length
   */
  fun <R>mapSpans(k: Int, transformation: (IntRange) -> R): List<R> {

    val acc = mutableListOf<R>()
    this.forEachSpan(k) { acc.add(transformation(it)) }
    return acc
  }
}
