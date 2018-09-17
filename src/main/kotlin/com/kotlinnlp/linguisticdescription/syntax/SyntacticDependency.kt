/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

import com.kotlinnlp.linguisticdescription.Deprel
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * The base interface implemented by all the syntactic dependencies.
 *
 * @property type the type of this dependency
 * @property direction the direction of the dependency, related to the governor
 */
sealed class SyntacticDependency<T: Any>(val type: T, val direction: Direction) {

  /**
   * The direction of the dependency.
   */
  enum class Direction {
    ROOT,
    LEFT,
    RIGHT,
    NULL
  }

  /**
   * Factory object.
   */
  companion object {

    /**
     * Create a new [SyntacticDependency] given its [SyntacticType].
     *
     * @param type the syntactic type
     * @param direction the direction of the dependency
     *
     * @return a new syntactic dependency
     */
    operator fun invoke(type: SyntacticType, direction: SyntacticDependency.Direction): SyntacticDependency.Base {

      val kClass: KClass<*> = syntacticDependencyClasses.getValue(type)
      val constructor: KFunction<Any> = kClass.constructors.last()

      @Suppress("UNCHECKED_CAST")
      return constructor.call(direction) as SyntacticDependency.Base
    }
  }

  /**
   * @return the string representation of this syntactic dependency
   */
  override fun toString(): kotlin.String = this.type.toString() + ":" + this.direction

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this syntactic dependency, checking only
   *         the type
   */
  fun softEquals(other: Any?): Boolean = other is SyntacticDependency<*> && other.type == this.type

  /**
   * @return a Boolean indicating whether the given [other] object is equal to this syntactic dependency
   */
  override fun equals(other: Any?): Boolean
    = other is SyntacticDependency<*> && other.type == this.type && other.direction == this.direction

  /**
   * @return the hash code of this [Deprel]
   */
  override fun hashCode(): Int = this.type.hashCode() * 31 + this.direction.hashCode()

  /**
   * The [SyntacticDependency] with a [SyntacticType].
   */
  abstract class Base(
    type: SyntacticType,
    direction: SyntacticDependency.Direction
  ) : SyntacticDependency<SyntacticType>(
    type = type,
    direction = direction
  )

  /**
   * The [SyntacticDependency] with a [kotlin.String] type.
   */
  class String(
    type: kotlin.String,
    direction: SyntacticDependency.Direction
  ) : SyntacticDependency<kotlin.String>(
    type = type,
    direction = direction
  )
}
