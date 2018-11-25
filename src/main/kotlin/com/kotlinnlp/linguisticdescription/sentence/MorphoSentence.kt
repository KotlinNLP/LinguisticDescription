/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.morphology.MorphologicalAnalysis
import com.kotlinnlp.linguisticdescription.sentence.token.FormToken

/**
 * A sentence with morphological and syntactic information.
 *
 * @property tokens the list of tokens of this sentence
 */
interface MorphoSentence<TokenType: FormToken> : Sentence<TokenType> {

  /**
   * The morphological analysis of the sentence (can be null)
   */
  val morphoAnalysis: MorphologicalAnalysis?
}
