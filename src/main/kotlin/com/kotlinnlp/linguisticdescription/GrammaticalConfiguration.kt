/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import com.kotlinnlp.linguisticdescription.syntax.SyntacticDependency
import java.io.Serializable

/**
 * The grammatical configuration of a token, defined by a POS and a syntax type.
 *
 * @property components the grammatical components
 */
data class GrammaticalConfiguration(val components: List<Component>) : Serializable {

  /**
   * The configuration type.
   *
   * @property Single if the configuration has only one component
   * @property Multiple if the configuration has more components
   */
  enum class Type { Single, Multiple }

  /**
   * A component of the configuration (more in case of composite tokens).
   *
   * @property syntacticDependency the syntactic dependency
   * @property pos the POS (can be null)
   */
  data class Component(val syntacticDependency: SyntacticDependency, val pos: POSTag? = null)

  /**
   * Build a [GrammaticalConfiguration] given the components as varargs.
   *
   * @param components the grammatical components
   */
  constructor(vararg components: Component): this(components.toList())

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L

    /**
     * The string used to separate an annotation with more components.
     */
    const val COMPONENTS_SEP = "+"
  }

  /**
   * The number of components of this configuration.
   */
  val size: Int = this.components.size

  /**
   * The type of configuration (Single or Multiple).
   */
  val type: Type = when (this.size) {
    0 -> throw RuntimeException("A grammatical configuration cannot be empty.")
    1 -> Type.Single
    else -> Type.Multiple
  }

  /**
   * The direction of the syntactic dependency defined in this configuration.
   */
  val direction: SyntacticDependency.Direction = this.components.first().syntacticDependency.direction

  /**
   * The string representation of all the dependencies of this configuration.
   */
  val dependencyToString: String by lazy {
    this.components.joinToString(COMPONENTS_SEP) { it.syntacticDependency.toString() }
  }

  /**
   * The string representation of all the POS of this configuration.
   */
  val posToString: String? by lazy {
    val s: String = this.components.mapNotNull { it.pos }.joinToString(COMPONENTS_SEP) { it.toString() }
    if (s.isNotEmpty()) s else null
  }
}
