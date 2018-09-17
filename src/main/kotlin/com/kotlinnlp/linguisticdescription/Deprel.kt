/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import java.io.Serializable

/**
 * The Deprel.
 *
 * @property labels the labels of the syntactic relation (more for a composite word)
 * @property direction the direction in a possible syntactic context for which this deprel is valid
 */
data class Deprel(val labels: List<String>, val direction: Direction = Direction.NULL) : Serializable {

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L
  }

  /**
   * The direction of the relation.
   */
  enum class Direction {
    ROOT,
    LEFT,
    RIGHT,
    NULL
  }

  /**
   * @return the string representation of this [Deprel]
   */
  override fun toString(): String = this.labels.joinToString("+") + ":" + this.direction

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this [Deprel], checking only the [label]
   */
  fun softEquals(other: Any?): Boolean = other is Deprel && other.labels == this.labels

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this [Deprel]
   */
  override fun equals(other: Any?): Boolean
    = other is Deprel && other.labels == this.labels && other.direction == this.direction

  /**
   * @return the hash code of this [Deprel]
   */
  override fun hashCode(): Int = this.labels.hashCode() * 31 + this.direction.hashCode()
}
