/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
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
    MISC("MISC"),
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
   * Build an [Entity] given an annotated segment.
   *
   * @param s the annotated segment
   */
  constructor(s: AnnotatedSegment): this(
    startToken = s.startToken,
    endToken = s.endToken,
    type = annotationsToTypes.getValue(s.annotation)
  )

  /**
   * @param tokens the list of all the sentence tokens in which to find the reference of this entity
   *
   * @return the form of this entity
   */
  fun getForm(tokens: List<Token>): String = this.getRefTokens(tokens).joinToString(separator = " ") {
    when (it) {
      is Trace -> "-"
      is Word -> it.form
      is WordTrace -> it.form
      else -> throw RuntimeException("Invalid token.")
    }
  }

  /**
   * @param tokens the list of tokens in which to find the reference of this entity
   *
   * @return a string representation of this entity
   */
  fun toString(tokens: List<Token>): String = "[%s] %s".format(
    this.type,
    this.getForm(tokens)
  )

  /**
   * @return the JSON object that represents this entity
   */
  fun toJSON(): JsonObject = json {
    obj(
      "startToken" to this@Entity.startToken,
      "endToken" to this@Entity.endToken,
      "type" to this@Entity.type.toString()
    )
  }
}
