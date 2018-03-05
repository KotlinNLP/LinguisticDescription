/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.kotlinnlp.linguisticdescription.sentence.token.Token
import com.kotlinnlp.linguisticdescription.sentence.token.Trace
import com.kotlinnlp.linguisticdescription.sentence.token.Word
import com.kotlinnlp.linguisticdescription.sentence.token.WordTrace

/**
 * A parsed token.
 *
 * @property tokens the list of tokens that compose this entity
 * @property type the entity type
 */
data class Entity(val tokens: List<Token>, val type: Type) {

  /**
   * The entity type.
   */
  enum class Type(val annotation: String) {
    Location("LOC"),
    Organization("ORG"),
    Person("PER"),
    Undefined("")
  }

  /**
   * Build an [Entity] given its list of tokens and its type annotation.
   */
  constructor(tokens: List<Token>, typeAnnotation: String): this(
    tokens = tokens,
    type = annotationsToTypes.getValue(typeAnnotation)
  )

  companion object {

    /**
     * A map of entity types associated by annotation.
     */
    private val annotationsToTypes: Map<String, Type> = Type.values().associateBy { it.annotation }
  }

  /**
   * @return a string representation of this entity
   */
  override fun toString(): String = "[%s] %s".format(
    this.type,
    this.tokens.joinToString(separator = " ") {
      when (it) {
        is Trace -> "-"
        is Word -> it.surface.form
        is WordTrace -> it.surface.form
        else -> throw RuntimeException("Invalid token.")
      }
    }
  )
}
