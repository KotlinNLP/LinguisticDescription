/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json

/**
 * A token with morphological and syntactic information.
 */
interface MorphoSyntacticToken : MorphoToken, SyntacticToken {

  /**
   * The label that defines the type of this token.
   */
  val type: String

  /**
   * @return the JSON object that represents this token
   */
  fun toJSON(): JsonObject {

    val jsonObject = json {
      obj(
        "id" to this@MorphoSyntacticToken.id,
        "lemma" to null,
        "head" to this@MorphoSyntacticToken.dependencyRelation.governor,
        "pos" to if (this@MorphoSyntacticToken.morphologies.isNotEmpty())
          this@MorphoSyntacticToken.morphologies.first().list.joinToString("-") { it.type.annotation }
        else
          null,
        "deprel" to this@MorphoSyntacticToken.dependencyRelation.deprel
      )
    }

    if (this is RealToken)
      jsonObject["form"] = (this as RealToken).form

    return jsonObject
  }
}
