/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

/**
 * A morphology entry.
 *
 * If [type] is [Type.Single] the list contains only one morphology, otherwise more.
 *
 * @property type the type of this entry (Single or Multiple)
 * @property list a list of morphologies
 */
data class MorphologyEntry(val type: Type, val list: List<Morphology>) {

  /**
   * The [MorphologyEntry] type.
   */
  enum class Type { Single, Multiple }

  /**
   * Build a [MorphologyEntry] given a list of [Morphology].
   *
   * @param morphologies the list of morphologies
   */
  constructor(morphologies: List<Morphology>): this(
    type = if (morphologies.size == 1) Type.Single else Type.Multiple,
    list = morphologies
  )

  /**
   * @return a string representation of this entry
   */
  override fun toString(): String = "[%s]\n\t\t%s".format(this.type, this.list.joinToString(separator = "\n\t\t"))

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

    other as MorphologyEntry

    if (this.toString() != other.toString()) return false

    return true
  }
}
