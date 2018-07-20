/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json

/**
 * A co-reference.
 *
 * @property tokenId the id of the referent token
 * @property sentenceId the sentence id of the referent token
 * @property confidence the confidence score of the co-reference
 */
data class CoReference(
  val tokenId: Int,
  val sentenceId: Int,
  val confidence: Double
) {

  /**
   * @return a string representation of this co-reference
   */
  override fun toString(): String = "%d-%s (%.2f%%)".format(
    this.sentenceId, this.tokenId.let { if (it >= 0) it.toString() else "ROOT" },
    100.0 * this.confidence)

  /**
   * @return the JSON object that represents this co-reference
   */
  fun toJSON(): JsonObject = json {

    val self = this@CoReference

    obj(
      "tokenId" to self.tokenId,
      "sentenceId" to self.sentenceId,
      "confidence" to self.confidence
    )
  }
}
