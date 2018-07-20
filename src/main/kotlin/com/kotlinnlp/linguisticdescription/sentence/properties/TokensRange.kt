/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.sentence.token.Token

/**
 * A multi-words expression.
 */
interface TokensRange {

  /**
   * The index of the first token of the expression
   */
  val startToken: Int

  /**
   * The index of the last token of the expression
   */
  val endToken: Int

  /**
   * @param tokens the list of all the sentence tokens in which to find the reference of this entity
   */
  fun getRefTokens(tokens: List<Token>): List<Token> = tokens.subList(this.startToken, this.endToken + 1)

  /**
   * @return the JSON object that represents this multi-words expression
   */
  fun toJSON(): JsonObject {
    TODO("make as abstract and implement for each implementation")
  }
}
