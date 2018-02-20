/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.compressor

import java.io.Serializable

/**
 * A container of morphology properties, used to map them to a unique index.
 *
 * @property list a list of morphology properties
 */
data class Properties(val list: List<Pair<String, String>>) : Serializable {

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L
  }

  /**
   * @return a string representation of this entry
   */
  override fun toString(): String = this.list
    .sortedBy { it.first }
    .joinToString(separator = ",") { "${it.first}=${it.second}" }

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

    other as Properties

    if (this.list != other.list) return false

    return true
  }
}
