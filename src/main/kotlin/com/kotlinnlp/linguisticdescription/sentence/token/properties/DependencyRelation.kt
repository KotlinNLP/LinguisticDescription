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
 * The head of a token.
 *
 * @property governor the id of the governor token (can be null if the governor is the root)
 * @property deprel the label of the dependency relation
 * @property attachmentScore the attachment confidence score
 */
data class DependencyRelation(
  val governor: Int?,
  val deprel: String,
  val attachmentScore: Double
) {

  /**
   * @return a string representation of this head
   */
  override fun toString(): String = "%s %s (%.2f%)".format(
    this.governor?.toString() ?: "R",
    this.deprel,
    this.attachmentScore)

  /**
   * @return the JSON object that represents this dependency relation
   */
  fun toJSON(): JsonObject = json {

    val self = this@DependencyRelation

    obj(
      "head" to self.governor,
      "relation" to self.deprel,
      "attachmentScore" to self.attachmentScore
    )
  }
}
