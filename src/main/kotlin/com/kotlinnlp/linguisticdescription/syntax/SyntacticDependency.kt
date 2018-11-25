/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * The base interface implemented by all the syntactic dependencies.
 *
 * @property type the type of this dependency
 * @property direction the direction of the dependency, related to the governor
 */
sealed class SyntacticDependency(open val type: Any, val direction: Direction) : Serializable {

  /**
   * The direction of the dependency.
   */
  enum class Direction {
    ROOT,
    LEFT,
    RIGHT,
    NULL;

    companion object {

      /**
       * Get the direction of a syntactic dependency.
       *
       * @param tokenIndex the index of the token to which the deprel must be assigned
       * @param headIndex the index of the token head (can be null)
       *
       * @return the direction of the syntactic dependency between the given token and its head
       */
      operator fun invoke(tokenIndex: Int, headIndex: Int?): SyntacticDependency.Direction = when {
        headIndex == null -> SyntacticDependency.Direction.ROOT
        tokenIndex < headIndex -> SyntacticDependency.Direction.LEFT
        else -> SyntacticDependency.Direction.RIGHT
      }
    }
  }

  /**
   * Factory object.
   */
  companion object {

    /**
     * Create a new [SyntacticDependency] given its annotation.
     *
     * @param annotation the syntactic type annotation
     * @param direction the direction of the dependency
     *
     * @return a new syntactic dependency
     */
    operator fun invoke(annotation: kotlin.String, direction: Direction = Direction.NULL): SyntacticDependency =

      try {

        val kClass: KClass<*> = syntacticDependencyClasses.getValue(SyntacticType.byAnnotation(annotation))
        val constructor: KFunction<Any> = kClass.constructors.last()

        constructor.call(direction) as SyntacticDependency

      } catch (e: SyntacticType.Factory.InvalidAnnotation) {
        SyntacticDependency.String(type = annotation, direction = direction)
      }
  }

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this syntactic dependency, checking only
   *         the type
   */
  fun softEquals(other: Any?): Boolean = other is SyntacticDependency && other.type == this.type

  /**
   * @param syntacticType a syntactic type
   *
   * @return true if the type of this dependency is a subtype of the given syntactic type, otherwise false
   */
  fun isSubTypeOf(syntacticType: SyntacticType): Boolean =
    this is SyntacticDependency.Base && this.type.isComposedBy(syntacticType)

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this syntactic dependency
   */
  override fun equals(other: Any?): Boolean
    = other is SyntacticDependency && other.type == this.type && other.direction == this.direction

  /**
   * @return the hash code of this syntactic dependency
   */
  override fun hashCode(): Int = this.type.hashCode() * 31 + this.direction.hashCode()

  /**
   * The [SyntacticDependency] with a [SyntacticType].
   */
  abstract class Base(override val type: SyntacticType, direction: Direction)
    : SyntacticDependency(type = type, direction = direction) {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }

    /**
     * @return the string representation of this syntactic dependency
     */
    override fun toString(): kotlin.String = this.type.annotation
  }

  /**
   * The [SyntacticDependency] with a [kotlin.String] type.
   */
  class String(override val type: kotlin.String, direction: Direction)
    : SyntacticDependency(type = type, direction = direction) {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }

    /**
     * @return the string representation of this syntactic dependency
     */
    override fun toString(): kotlin.String = this.type
  }
}
