/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

/**
 * The head of a token.
 *
 * @property id the id of the head token
 * @property confidence the confidence score of the attachment to this head
 */
data class Head(
  val id: Int,
  val confidence: Double
) {

  /**
   * @return a string representation of this head
   */
  override fun toString(): String = "%d (%.2f%%)".format(this.id, 100.0 * this.confidence)
}
