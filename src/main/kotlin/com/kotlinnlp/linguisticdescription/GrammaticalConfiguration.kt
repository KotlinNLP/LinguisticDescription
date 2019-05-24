/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import com.kotlinnlp.linguisticdescription.morphology.Morphology
import com.kotlinnlp.linguisticdescription.syntax.SyntacticDependency
import com.kotlinnlp.linguisticdescription.syntax.dependencies.Contin
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
  data class Component(val syntacticDependency: SyntacticDependency, val pos: POSTag? = null) : Serializable {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }
  }

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
    private const val COMPONENTS_SEP = "+"
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

  /**
   * Check components size.
   */
  init {
    require(this.components.isNotEmpty()) { "A GrammaticalConfiguration must contain at least 1 component." }
  }

  /**
   * Note: this method should be used only if this configuration contains 'Base' components only (not String).
   *
   * @param morphology a morphology
   *
   * @return true if this all the components of this grammatical configuration is compatible with the given morphology,
   *         otherwise false
   */
  fun isCompatible(morphology: Morphology): Boolean =
    this.components.size == morphology.components.size &&
      this.components.zip(morphology.components).all {
        it.first.pos != null && (it.first.pos as POSTag.Base).type.baseAnnotation == it.second.pos.baseAnnotation
      }

  /**
   * Note: this method should be used only if this configuration contains 'Base' components only (not String).
   *
   * @param morphology a morphology
   *
   * @return true if any 'CONTIN' component of this grammatical configuration is compatible with the given morphology,
   *         otherwise false
   */
  fun isPartiallyCompatible(morphology: Morphology): Boolean =
    this.components.any {
      (it.syntacticDependency as SyntacticDependency.Base) is Contin &&
        it.pos != null &&
        morphology.components.any { morpho -> morpho.pos.baseAnnotation == (it.pos as POSTag.Base).type.baseAnnotation }
    }

  /**
   * @return whether this grammatical configuration defines a content word with a single morphology
   */
  fun isSingleContentWord(): Boolean =
    this.type == GrammaticalConfiguration.Type.Single
      && (this.components.single().pos as POSTag.Base).type.isContentWord
}
