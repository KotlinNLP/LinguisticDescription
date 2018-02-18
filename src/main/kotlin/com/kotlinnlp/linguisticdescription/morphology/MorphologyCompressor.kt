/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.obj
import com.beust.klaxon.string
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.morphologies.MorphologyFactory
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyPropertyFactory
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyType
import java.io.Serializable

/**
 * An helper that optimizes the memory load for the morphologies, mapping them to indices.
 */
class MorphologyCompressor : Serializable {

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L
  }

  /**
   * A container of morphology properties, used to map them to a unique index.
   *
   * @property properties a list of morphology properties
   */
  private data class Properties(val properties: List<Pair<String, String>>) : Serializable {

    companion object {

      /**
       * Private val used to serialize the class (needed by Serializable).
       */
      @Suppress("unused")
      private const val serialVersionUID: Long = 1L
    }

    override fun toString(): String = this.properties
      .sortedBy { it.first }
      .joinToString(separator = ",") { "${it.first}=${it.second}" }

    override fun hashCode(): Int = this.toString().hashCode()

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Properties

      if (this.properties != other.properties) return false

      return true
    }
  }

  /**
   * The multiplier coefficient of the type index, used to encode a morphology.
   */
  private val typeIndexCoeff: Int = 1.0e03.toInt()

  /**
   * The multiplier coefficient of the lemma index, used to encode a morphology.
   */
  private val lemmaIndexCoeff: Long = 1.0e06.toLong()

  /**
   * The BiMap of unique indices to lemmas.
   */
  private val lemmasBiMap: BiMap<Int, String> = HashBiMap.create()

  /**
   * The map of morphology types associated by annotation.
   */
  private val annotationsToTypesMap: Map<String, MorphologyType> = MorphologyType.values().associateBy { it.annotation }

  /**
   * The BiMap of morphology annotations associated by index.
   */
  private val indicesToAnnotationsBiMap: BiMap<Int, String> = HashBiMap.create(
    this.annotationsToTypesMap.keys.mapIndexed { i, index -> Pair(i, index) }.associate { it }
  )

  /**
   * The BiMap of unique indices to morphology properties.
   */
  private val propertiesBiMap: BiMap<Int, Properties> = HashBiMap.create()

  /**
   * @param morphologyObj a morphology JSON object
   *
   * @return the encoded index of [morphologyObj]
   */
  fun encodeMorphology(morphologyObj: JsonObject): Long {

    val typeAnnotation: String = morphologyObj.string("type")!!

    if (typeAnnotation !in this.annotationsToTypesMap) throw InvalidMorphologyType(typeAnnotation)

    val lemmaIndex: Int = this.encodeLemma(morphologyObj.string("lemma")!!)
    val typeIndex: Int = this.indicesToAnnotationsBiMap.inverse().getValue(typeAnnotation)
    val propertiesIndex: Int = this.encodeJSONProperties(morphologyObj.obj("properties")!!)

    return this.lemmaIndexCoeff * lemmaIndex + this.typeIndexCoeff * typeIndex + propertiesIndex
  }

  /**
   * Decode an encoded morphology.
   *
   * @param encodedMorphology an encoded morphology
   *
   * @return the decoded morphology object
   */
  fun decodeMorphology(encodedMorphology: Long): Morphology = MorphologyFactory(
    lemma = this.decodeLemma(encodedMorphology),
    type = this.decodeType(encodedMorphology),
    properties = this.decodeProperties(encodedMorphology)
  )

  /**
   * @param lemma a lemma
   *
   * @return the index that encodes the given lemma
   */
  private fun encodeLemma(lemma: String): Int {

    val inversedMap: Map<String, Int> = this.lemmasBiMap.inverse()

    return if (lemma in inversedMap) {
      inversedMap.getValue(lemma)
    } else {
      this.lemmasBiMap[this.lemmasBiMap.size] = lemma
      this.lemmasBiMap.size
    }
  }

  /**
   * @param propertiesObject the JSON object of the properties of a morphology
   *
   * @return the index that encodes the given properties
   */
  private fun encodeJSONProperties(propertiesObject: JsonObject): Int {

    val properties = Properties(propertiesObject.filter { it.value != null }.map { Pair(it.key, it.value as String) })
    val inversedMap: Map<Properties, Int> = this.propertiesBiMap.inverse()

    return if (properties in inversedMap) {
      inversedMap.getValue(properties)
    } else {
      this.propertiesBiMap[this.propertiesBiMap.size] = properties
      this.propertiesBiMap.size
    }
  }

  /**
   * @param encodedMorphology an encoded morphology
   *
   * @return the lemma of the given [encodedMorphology]
   */
  private fun decodeLemma(encodedMorphology: Long): String =
    this.lemmasBiMap[(encodedMorphology / this.lemmaIndexCoeff).toInt()]!!

  /**
   * @param encodedMorphology an encoded morphology
   *
   * @return the morphology type of the given [encodedMorphology]
   */
  private fun decodeType(encodedMorphology: Long): MorphologyType {

    val typeRemainder: Int = (encodedMorphology % this.lemmaIndexCoeff).toInt()
    val typeAnnotation: String = this.indicesToAnnotationsBiMap.getValue(typeRemainder / this.typeIndexCoeff)

    return this.annotationsToTypesMap[typeAnnotation]!!
  }

  /**
   * @param encodedMorphology an encoded morphology
   *
   * @return the map of properties types to [MorphologyProperty] objects associated to the given [encodedMorphology]
   */
  private fun decodeProperties(encodedMorphology: Long): Map<String, MorphologyProperty> {

    val properties: Properties = this.propertiesBiMap.getValue((encodedMorphology % this.typeIndexCoeff).toInt())

    return properties.properties.associate {
      Pair(
        it.first,
        MorphologyPropertyFactory(propertyType = it.first, valueAnnotation = it.second)
      )
    }
  }
}
