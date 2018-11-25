/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.GrammaticalConfiguration
import com.kotlinnlp.linguisticdescription.POSTag
import com.kotlinnlp.linguisticdescription.syntax.SyntacticDependency
import com.kotlinnlp.linguisticdescription.syntax.dependencies.Top
import com.kotlinnlp.linguisticdescription.syntax.dependencies.Unknown

/**
 * A morphology of a lexical form.
 *
 * If [type] is [Type.Single] the list contains only one single morphology, otherwise more.
 *
 * @property components the list of single morphologies that compose this morphology (one for a Single morphology)
 */
open class Morphology(val components: List<SingleMorphology>) {

  /**
   * The [Morphology] type.
   */
  enum class Type { Single, Multiple }

  /**
   * Build a [Morphology] given a single [SingleMorphology].
   *
   * @param morphology a single morphology
   */
  constructor(morphology: SingleMorphology): this(listOf(morphology))

  /**
   * The type of this morphology (Single or Multiple).
   */
  val type: Type = when (this.components.size) {
    0 -> throw RuntimeException("The components list of a Morphology cannot be empty.")
    1 -> Type.Single
    else -> Type.Multiple
  }

  /**
   * @return a string representation of this morphology
   */
  override fun toString(): String = "[%s]\n\t\t%s".format(this.type, this.components.joinToString(separator = "\n\t\t"))

  /**
   * @return the hash code of this object
   */
  override fun hashCode(): Int = this.toString().hashCode()

  /**
   * @param other another object
   *
   * @return a boolean indicating if this object is equal to the [other] object
   */
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Morphology

    if (this.toString() != other.toString()) return false

    return true
  }

  /**
   * @return the JSON object that represents this morphology
   */
  open fun toJSON(): JsonObject = json {

    val self = this@Morphology

    obj(
      "type" to self.type.toString().toUpperCase(),
      "list" to array(self.components.map { it.toJSON() })
    )
  }

  /**
   * Build a grammatical configuration from this [Morphology] with TOP or UNKNOWN dependency.
   *
   * @param direction the direction to set to the grammatical configuration
   *
   * @return a new grammatical configuration built from this morphology
   */
  fun buildUnknownConfig(direction: SyntacticDependency.Direction) = GrammaticalConfiguration(
    components = this.components.mapIndexed { i, it ->
      GrammaticalConfiguration.Component(
        syntacticDependency = if (i == 0 && direction == SyntacticDependency.Direction.ROOT)
          Top(direction)
        else
          Unknown(direction),
        pos = POSTag.Base(POS.byBaseAnnotation(it.pos.baseAnnotation)))
    }
  )
}
