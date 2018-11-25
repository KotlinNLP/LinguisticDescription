/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.Preposition
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Article
import com.kotlinnlp.linguisticdescription.sentence.properties.MultiWords
import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.DateTime

/**
 * The Morphological Analysis.
 *
 * @property tokensMorphologies the list of morphologies associated to each token considered individually;
 *                              the size of the list corresponds to the number of tokens of the sentence
 * @property multiWords the list of multi-words morphologies
 * @property dateTimes the list of date-times
 */
data class MorphologicalAnalysis(
  val tokensMorphologies: List<Morphologies>,
  val multiWords: List<MultiWords>,
  val dateTimes: List<DateTime>
) {

  /**
   * The union of the token morphologies and the morphologies of multi-words that the token introduces.
   */
  val startMorphologies: List<Morphologies> by lazy {
    this.tokensMorphologies.mapIndexed { tokenIndex, morphologies ->
      Morphologies(morphologies + this.getTokenStartMWMorphologies(tokenIndex))
    }
  }

  /**
   * The morphologies of multi-words that involve the token, but do not start with.
   */
  val middleMWMorphologies: List<Morphologies> by lazy {
    this.tokensMorphologies.indices.map { tokenIndex -> this.getTokenMiddleMWMorphologies(tokenIndex) }
  }

  /**
   * All the possible morphologies of the token (multi-words included).
   */
  val allMorphologies: List<Morphologies> by lazy {
    this.tokensMorphologies.indices.map { tokenIndex ->
      Morphologies(
        this.startMorphologies[tokenIndex] +
          this.middleMWMorphologies[tokenIndex] +
          this.getPrepArtMWMorphologies(tokenIndex)
      )
    }
  }

  /**
   * Map the index of each token to the list of multi-words morphologies in which it is involved.
   */
  val tokensToMultiWords: Map<Int, List<MultiWords>> by lazy {
    mapOf(*(0 until this.tokensMorphologies.size)
      .map { tokenIndex -> Pair(tokenIndex, this.multiWords.filter { tokenIndex in (it.startToken..it.endToken) }) }
      .toTypedArray())
  }

  /**
   * Get the list of multi-words morphologies in which a given token is involved.
   *
   * @param tokenIndex the index of a token
   *
   * @return a list of multi-words morphologies or null if no one has been found
   */
  fun getInvolvedMultiWords(tokenIndex: Int): List<MultiWords>? = this.tokensToMultiWords[tokenIndex]

  /**
   * Return the morphologies of the multi-words that start with a given token.
   *
   * @param tokenIndex the index of the token
   *
   * @return a list of morphologies
   */
  private fun getTokenStartMWMorphologies(tokenIndex: Int) = Morphologies(
    this.multiWords
      .filter { tokenIndex == it.startToken }
      .flatMap { it.morphologies }
  )

  /**
   * Return the morphologies of the multi-words that involve a given token, but do not start with it.
   *
   * @param tokenIndex the index of the token
   *
   * @return a list of morphologies
   */
  private fun getTokenMiddleMWMorphologies(tokenIndex: Int) = Morphologies(
    this.multiWords
      .filter { tokenIndex in (it.startToken + 1)..it.endToken }
      .flatMap { it.morphologies }
  )

  /**
   * @param tokenIndex the index of the token
   *
   * @return a list of morphologies
   */
  private fun getPrepArtMWMorphologies(tokenIndex: Int): Morphologies {

    val prepArt: Morphology? = this.tokensMorphologies[tokenIndex].firstOrNull {
      it.components.size == 2 && it.components[0] is Preposition && it.components[1] is Article
    }

    return if (prepArt != null)
      Morphologies(this.multiWords
        .filter { tokenIndex == it.endToken }
        .flatMap { it.morphologies }
        .filter { it.components.single() is Preposition }
        .map { Morphology(listOf(it.components.single(), prepArt.components[1])) })
    else
      Morphologies()
  }
}
