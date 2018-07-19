/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.sentence.token.RealToken
import com.kotlinnlp.linguisticdescription.sentence.token.Token

/**
 * A sentence.
 */
interface Sentence<TokenType: Token> {

  companion object {

    /**
     * A separator used to construct the text of the sentence.
     * One or more separators are put between tokens when there is a gap between the end of a token and the start of
     * the following one.
     */
    private const val TOKENS_SEPARATOR = " "
  }

  /**
   * The list of tokens of this sentence.
   */
  val tokens: List<TokenType>

  /**
   * @return the text of this sentence built from its [tokens]
   */
  fun buildText(): String {

    val text = StringBuffer()
    var tokenShouldStart = (this as? RealSentence)?.position?.start ?: 0

    this.tokens.filter { it is RealToken }.forEach { it as RealToken

      text.append(TOKENS_SEPARATOR.repeat(it.position.start - tokenShouldStart)) // multi-spaces start token padding
      text.append(it.form)

      tokenShouldStart = it.position.end + 1
    }

    if (this is RealSentence) {
      // multi-spaces end sentence padding
      text.append(TOKENS_SEPARATOR.repeat(this.position.end - tokenShouldStart + 1))
    }

    return text.toString()
  }
}
