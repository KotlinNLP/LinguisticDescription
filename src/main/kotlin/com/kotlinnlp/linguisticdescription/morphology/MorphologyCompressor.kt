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
   * The encoded morphology of an entry of the [MorphologyDictionary].
   */
  inner class EncodedMorphology(val lemma: String, val typeIndex: Int, val propertiesIndex: Int) : Serializable {

    /**
     * @return the morphology decoded from this one
     */
    fun decode(): Morphology {

      val typeAnnotation: String = this@MorphologyCompressor.indicesToAnnotationsBiMap[this.typeIndex]!!

      if (typeAnnotation !in this@MorphologyCompressor.annotationsToTypesMap) {
        throw InvalidMorphologyType(typeAnnotation)
      }

      return MorphologyFactory(
        lemma = this.lemma,
        type = this@MorphologyCompressor.annotationsToTypesMap[typeAnnotation]!!,
        properties = this@MorphologyCompressor.decodeProperties(this.propertiesIndex)
      )
    }

    override fun hashCode(): Int = "%s\t%d\t%d".format(this.lemma, this.typeIndex, this.propertiesIndex).hashCode()

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as EncodedMorphology

      if (this.lemma != other.lemma) return false
      if (this.typeIndex != other.typeIndex) return false
      if (this.propertiesIndex != other.propertiesIndex) return false

      return true
    }
  }

  /**
   * A container of morphology properties, used to map them to a unique index.
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
      .map { "${it.first} = ${it.second}" }
      .joinToString { "\t" }

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
   * @return the encoded morphology object of [morphologyObj]
   */
  fun encodeMorphology(morphologyObj: JsonObject): EncodedMorphology {

    val typeAnnotation: String = morphologyObj.string("type")!!

    if (typeAnnotation !in this.annotationsToTypesMap) throw InvalidMorphologyType(typeAnnotation)

    return EncodedMorphology(
      lemma = morphologyObj.string("lemma")!!,
      typeIndex = this.indicesToAnnotationsBiMap.inverse().getValue(typeAnnotation),
      propertiesIndex = this.encodeJSONProperties(morphologyObj.obj("properties")!!)
    )
  }

  /**
   * @param propertiesObject the JSON object of the properties of a morphology
   *
   * @return the index that encodes the given properties
   */
  private fun encodeJSONProperties(propertiesObject: JsonObject): Int {

    val properties = Properties(propertiesObject.filter { it.value != null }.map { Pair(it.value as String, it.key) })
    val inversedMap: Map<Properties, Int> = this.propertiesBiMap.inverse()

    return if (properties in inversedMap) {
      inversedMap.getValue(properties)
    } else {
      this.propertiesBiMap[this.propertiesBiMap.size] = properties
      this.propertiesBiMap.size
    }
  }

  /**
   * @param index the index of an encoded morphology properties object
   *
   * @return the map of properties types to [MorphologyProperty] objects encoded with the given [index]
   */
  private fun decodeProperties(index: Int): Map<String, MorphologyProperty> {

    val properties: Properties = this.propertiesBiMap.getValue(index)

    return properties.properties.associate {
      Pair(
        it.first,
        MorphologyPropertyFactory(propertyType = it.first, valueAnnotation = it.second)
      )
    }
  }
}
