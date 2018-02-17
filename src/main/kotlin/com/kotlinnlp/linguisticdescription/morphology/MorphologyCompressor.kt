/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.obj
import com.beust.klaxon.string
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.morphologies.MorphologyFactory
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyPropertyFactory
import com.kotlinnlp.linguisticdescription.utils.InvalidMorphologyType

/**
 * An helper that optimizes the memory load for the morphologies, mapping them to indices.
 */
class MorphologyCompressor {

  /**
   * The map of morphology types associated by annotation.
   */
  private val annotationsMap: Map<String, MorphologyType> = MorphologyType.values().associateBy { it.annotation }

  /**
   * The BiMap of unique indices to morphologies in JSON string format.
   */
  private val jsonMorphologiesBiMap: BiMap<Int, String> = HashBiMap.create()

  /**
   * @param morphologyObj a JSON object of a morphology
   *
   * @return the index associated to the given morphology
   */
  fun morphologyObjToIndex(morphologyObj: JsonObject): Int {

    val jsonMorphology = morphologyObj.toJsonString()

    if (jsonMorphology !in this.jsonMorphologiesBiMap.inverse()) {
      this.jsonMorphologiesBiMap[this.jsonMorphologiesBiMap.size] = jsonMorphology
    }

    return this.jsonMorphologiesBiMap.inverse()[jsonMorphology]!!
  }

  /**
   * @param index the index of a morphology
   *
   * @return the morphology associated to the given [index]
   */
  fun getMorphology(index: Int): Morphology {

    val jsonMorphology = this.jsonMorphologiesBiMap.getValue(index)
    val morphoObj = Parser().parse(jsonMorphology) as JsonObject
    val typeAnnotation: String = morphoObj.string("type")!!

    if (typeAnnotation !in this.annotationsMap) throw InvalidMorphologyType(typeAnnotation)

    return MorphologyFactory(
      lemma = morphoObj.string("lemma")!!,
      type = this.annotationsMap[typeAnnotation]!!,
      properties = morphoObj.obj("properties")!!
        .filter { it.value != null }
        .mapValues { MorphologyPropertyFactory(propertyType = it.key, valueAnnotation = it.value as String) }
    )
  }
}
