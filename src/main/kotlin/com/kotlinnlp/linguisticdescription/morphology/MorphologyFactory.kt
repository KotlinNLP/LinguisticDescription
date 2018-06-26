/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.utils.MissingMorphologyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

/**
 * The factory of a new [Morphology].
 */
object MorphologyFactory {

  /**
   * Create a new [Morphology] given its [properties].
   *
   * @param lemma the lemma of the morphology
   * @param type the morphology type
   * @param properties the map of property names to their values (optional, unnecessary adding properties are ignored)
   *
   * @throws MissingMorphologyProperty when a required property is missing
   *
   * @return a new morphology
   */
  operator fun invoke(lemma: String,
                      type: MorphologyType,
                      properties: Map<String, MorphologyProperty> = mapOf()): Morphology {

    require(type != MorphologyType.Num) {
      "'NUM' morphologies cannot be created with the factory because they have an adding 'numericForm' property."
    }

    val kClass: KClass<*> = morphologyClasses[type]!!
    val constructor: KFunction<Any> = kClass.constructors.last()

    val keywordArgs: Map<KParameter, Any?> = constructor.parameters.associate {

      val propertyName: String = it.name!!
      val isLemma: Boolean = propertyName == "lemma"

      if (!isLemma && propertyName !in properties)
        throw MissingMorphologyProperty(propertyName = propertyName, morphologyType = type, lemma = lemma)

      Pair(it, if (isLemma) lemma else properties[propertyName]!!)
    }

    return constructor.callBy(keywordArgs) as Morphology
  }
}
