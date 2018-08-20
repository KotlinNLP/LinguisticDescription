/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.sentence.token.TokenIdentificable

/**
 * A sentence with identificable tokens.
 */
abstract class SentenceIdentificable<TokenType: TokenIdentificable> : Sentence<TokenType> {

  /**
   * A map that associates each token id to each index within the [tokens] list.
   */
  private lateinit var tokensIdsToIndices: Map<Int, Int>

  /**
   * The tokens of this sentence associated by ID.
   */
  private lateinit var tokensById: Map<Int, TokenType>

  /**
   * Get the index of a given token within the [tokens] list.
   *
   * @param id the id of a token
   *
   * @throws NoSuchElementException if the given id does not refers to any token of this sentence
   *
   * @return the index of the token within the [tokens] list
   */
  fun getTokenIndex(id: Int): Int {

    if (!this::tokensIdsToIndices.isInitialized) reIndexTokens()

    return this.tokensIdsToIndices.getValue(id)
  }

  /**
   * Get a token of this sentence given its id.
   *
   * @param id the id of a token
   *
   * @throws NoSuchElementException if the given id does not refers to any token of this sentence
   *
   * @return the token of this sentence with the given id
   */
  fun getTokenById(id: Int): TokenType {

    if (!this::tokensById.isInitialized) reIndexTokens()

    return this.tokensById.getValue(id)
  }

  /**
   * Re-index the tokens-ids association.
   * It must be called each time the [tokens] list changes.
   */
  protected fun reIndexTokens() {

    this.tokensIdsToIndices = this.tokens.withIndex().associate { (i, it) -> it.id to i }

    this.tokensById = this.tokens.associateBy { it.id }
  }
}
