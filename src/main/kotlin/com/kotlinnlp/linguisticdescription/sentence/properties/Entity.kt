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
import com.kotlinnlp.utils.JSONSerializable

/**
 * An entity found as sequence of tokens.
 *
 * @property startToken the index of the first token of this numeric expression, within the input tokens list
 * @property endToken the index of the last token of this numeric expression, within the input tokens list
 * @property startChar the index of the first char of the entity
 * @property endChar the index of the last char of the entity
 * @property type the entity type
 * @property score the confidence score
 */
data class Entity(
  override val startToken: Int,
  override val endToken: Int,
  override val startChar: Int,
  override val endChar: Int,
  val type: Type,
  val score: Double
) : TokensRange, JSONSerializable {

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
   * @param score the confidence score
   */
  constructor(startToken: Int, endToken: Int, typeAnnotation: String, score: Double): this(
    startToken = startToken,
    endToken = endToken,
    type = annotationsToTypes.getValue(typeAnnotation),
    score = score
  )

  /**
   * Build an [Entity] given an annotated segment.
   *
   * @param segment the annotated segment
   */
  constructor(segment: AnnotatedSegment): this(
    startToken = segment.startToken,
    endToken = segment.endToken,
    type = annotationsToTypes.getValue(segment.annotation),
    score = segment.score
  )

  /**
   * @param tokens the list of all the sentence tokens in which to find the reference of this entity
   *
   * @return the form of this entity
   */
  fun getForm(tokens: List<Token>): String = this.getRefTokens(tokens).joinToString(separator = " ") {
    if (it is FormToken) it.form else "-" // TODO: find a better way to represent non-form tokens
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
}
