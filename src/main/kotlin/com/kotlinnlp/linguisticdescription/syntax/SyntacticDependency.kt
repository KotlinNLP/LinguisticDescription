/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * The base interface implemented by all the syntactic dependencies.
 */
interface SyntacticDependency<T> {

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
    operator fun invoke(type: SyntacticType, direction: SyntacticDependency.Direction): SyntacticDependency<SyntacticType> {

      val kClass: KClass<*> = syntacticDependencyClasses.getValue(type)
      val constructor: KFunction<Any> = kClass.constructors.last()

      @Suppress("UNCHECKED_CAST")
      return constructor.call(direction) as SyntacticDependency<SyntacticType>
    }
  }

  /**
   * The type of this dependency.
   */
  val type: T

  /**
   * The direction of the dependency, related to the governor.
   */
  val direction: Direction
}
