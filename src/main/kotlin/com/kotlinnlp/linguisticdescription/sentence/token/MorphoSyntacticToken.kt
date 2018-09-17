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
interface MorphoSyntacticToken : ScoredMorphoToken, SyntacticToken {

  /**
   * The label that defines the type of this token.
   */
  val type: String

  /**
   * @return the JSON object that represents this token
   */
  fun toJSON(): JsonObject {

    val jsonObject = json {

      val self = this@MorphoSyntacticToken

      obj(
        "id" to self.id,
        "type" to self.type,
        "pos" to self.pos?.labels?.joinToString("+"),
        "dependency" to self.syntacticRelation.toJSON(),
        "coReferences" to self.coReferences?.let { array(it.map { it.toJSON() }) },
        "semanticRelations" to self.semanticRelations?.let { array(it.map { it.toString() }) },
        "morphology" to if (self.morphologies.isNotEmpty()) array(self.morphologies.map { it.toJSON() }) else null
      )
    }

    if (this is RealToken) {
      jsonObject["surface"] = json {
        obj(
          "form" to this@MorphoSyntacticToken.form,
          "translitForm" to this@MorphoSyntacticToken.form // TODO: set it properly
        )
      }
      jsonObject["position"] = this.position.toJSON()
    }

    return jsonObject
  }
}
