/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.multiwords

import com.kotlinnlp.linguisticdescription.sentence.token.*

/**
 * An entity found as sequence of tokens.
 *
 * @property startToken the index of the first token of this numeric expression, within the input tokens list
 * @property endToken the index of the last token of this numeric expression, within the input tokens list
 * @property type the entity type
 */
data class Entity(override val startToken: Int, override val endToken: Int, val type: Type) : TokensRange {

  /**
   * The entity type.
   */
  enum class Type(val annotation: String) {
    Location("LOC"),
    Organization("ORG"),
    Person("PER"),
    GeoPolitical("GPE"),
    Undefined("")
  }

  companion object {

    /**
     * A map of entity types associated by annotation.
     */
    private val annotationsToTypes: Map<String, Type> = Type.values().associateBy { it.annotation }
  }

  /**
   * Build an [Entity] given its list of tokens and its type annotation.
   *
   * @param startToken the index of the first token of this numeric expression, within the input tokens list
   * @param endToken the index of the last token of this numeric expression, within the input tokens list
   * @param typeAnnotation the annotation string of the type of this entity
   */
  constructor(startToken: Int, endToken: Int, typeAnnotation: String): this(
    startToken = startToken,
    endToken = endToken,
    type = annotationsToTypes.getValue(typeAnnotation)
  )

  /**
   * @param tokens the list of tokens in which to find the reference of this entity
   *
   * @return a string representation of this entity
   */
  fun toString(tokens: List<Token>): String = "[%s] %s".format(
    this.type,
    this.getRefTokens(tokens).joinToString(separator = " ") {
      when (it) {
        is Trace -> "-"
        is Word -> it.form
        is WordTrace -> it.form
        else -> throw RuntimeException("Invalid token.")
      }
    }
  )
}
