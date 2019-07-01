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
 * The position of an item.
 *
 * @property index the index of this item
 * @property start the index of the starting char of this item within the original text
 * @property end the index of the ending char of this item within the original text
 */
data class Position(
  val index: Int,
  val start: Int,
  val end: Int
) {

  /**
   * The length of the item.
   */
  val length: Int = this.end - this.start + 1

  /**
   * @return the JSON object that represents this position
   */
  fun toJSON(): JsonObject = json {

    val self = this@Position

    obj(
      "start" to self.start,
      "end" to self.end,
      "index" to self.index
    )
  }

  override fun equals(other: Any?): Boolean {

    if (other !is Position) return false

    return other.start == this.start && other.end == this.end
  }

  override fun hashCode(): Int = 31 * this.start + this.end
}
