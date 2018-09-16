/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.DependencyRelation

/**
 * The syntactic relation of a token.
 *
 * @property governor the id of the governor token (can be null if the governor is the root)
 * @property dependencyRelation the dependency relation
 * @property attachmentScore the attachment confidence score
 */
data class SyntacticRelation(
  val governor: Int?,
  val dependencyRelation: DependencyRelation,
  val attachmentScore: Double
) {

  /**
   * @return a string representation of this head
   */
  override fun toString(): String = "%s %s (%.2f%)".format(
    this.governor?.toString() ?: "R",
    this.dependencyRelation,
    this.attachmentScore)

  /**
   * @return the JSON object that represents this dependency relation
   */
  fun toJSON(): JsonObject = json {

    val self = this@SyntacticRelation

    obj(
      "head" to self.governor,
      "relation" to obj(
        "posTags" to self.dependencyRelation.posTag?.let { array(it.labels) },
        "deprels" to array(self.dependencyRelation.deprel.labels)
      ),
      "attachmentScore" to self.attachmentScore
    )
  }
}
