/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import com.kotlinnlp.linguisticdescription.morphology.POS
import java.io.Serializable

/**
 * The Part-Of-Speech (POS) tag.
 *
 * @property type the POS type
 */
sealed class POSTag(open val type: Any): Serializable {

  /**
   * Factory object.
   */
  companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L

    /**
     * Create a new [POSTag] given its annotation.
     *
     * @param annotation a POS annotation
     *
     * @return a new [POSTag] with the given annotation
     */
    operator fun invoke(annotation: kotlin.String): POSTag =
      try { Base(POS.byAnnotation(annotation)) } catch (e: POS.Factory.InvalidAnnotation) { String(annotation) }
  }

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this [POSTag]
   */
  override fun equals(other: Any?): Boolean
    = other is POSTag && other.type == this.type

  /**
   * @return the hash code of this [POSTag]
   */
  override fun hashCode(): Int = this.type.hashCode()

  /**
   * @param pos a POS type
   *
   * @return true if the type of this POS tag is a subtype of the given POS, otherwise false
   */
  fun isSubTypeOf(pos: POS): Boolean = this is POSTag.Base && this.type.isComposedBy(pos)

  /**
   * The [POSTag] with the [POS] type.
   */
  class Base(override val type: POS) : POSTag(type) {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }

    /**
     * @return a string representation of this POS tag
     */
    override fun toString(): kotlin.String = this.type.annotation
  }

  /**
   * The [POSTag] with the [kotlin.String] type.
   */
  class String(override val type: kotlin.String) : POSTag(type) {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }

    /**
     * @return a string representation of this POS tag
     */
    override fun toString(): kotlin.String = this.type
  }
}
