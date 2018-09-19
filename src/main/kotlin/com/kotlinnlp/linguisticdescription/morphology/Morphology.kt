/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json

/**
 * A morphology of a lexical form.
 *
 * If [type] is [Type.Single] the list contains only one single morphology, otherwise more.
 *
 * @property type the type of this morphology (Single or Multiple)
 * @property components the list of single morphologies that compose this morphology (one for a Single morphology)
 */
open class Morphology(val type: Type, val components: List<SingleMorphology>) {

  /**
   * The [Morphology] type.
   */
  enum class Type { Single, Multiple }

  /**
   * Build a [Morphology] given a list of [SingleMorphology].
   *
   * @param morphologies the list of single morphologies
   */
  constructor(morphologies: List<SingleMorphology>): this(
    type = if (morphologies.size == 1) Type.Single else Type.Multiple,
    components = morphologies
  )

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
}
