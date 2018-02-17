/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.morphologies.MorphologyFactory
import java.io.Serializable

/**
 * The encoded morphology of an entry of the [MorphologyDictionary].
 *
 * @property lemmaIndex
 * @property typeIndex
 * @property propertiesIndex
 * @param compressor
 */
class EncodedMorphology(
  val lemmaIndex: Int,
  val typeIndex: Int,
  val propertiesIndex: Int,
  private val compressor: MorphologyCompressor
) : Serializable {

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L
  }

  /**
   * @return the morphology decoded from this one
   */
  fun decode(): Morphology = MorphologyFactory(
    lemma = this.compressor.decodeLemma(this.lemmaIndex),
    type = this.compressor.decodeType(this.typeIndex),
    properties = this.compressor.decodeProperties(this.propertiesIndex)
  )

  override fun hashCode(): Int = "%d %d %d".format(this.lemmaIndex, this.typeIndex, this.propertiesIndex).hashCode()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as EncodedMorphology

    if (this.lemmaIndex != other.lemmaIndex) return false
    if (this.typeIndex != other.typeIndex) return false
    if (this.propertiesIndex != other.propertiesIndex) return false

    return true
  }
}
