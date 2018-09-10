/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.MissingMorphologyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

/**
 * The factory of a new [SingleMorphology].
 */
object MorphologyFactory {

  /**
   * Create a new [SingleMorphology] given its [properties].
   *
   * @param lemma the lemma of the morphology
   * @param pos the POS of the morphology
   * @param properties the map of property names to their values (optional, unnecessary adding properties are ignored)
   * @param allowIncompleteProperties allow to build the morphology even if the [properties] map does not contain all
   *                                  the required properties (default = false)
   *
   * @throws MissingMorphologyProperty when a required property is missing
   *
   * @return a new morphology
   */
  operator fun invoke(lemma: String,
                      pos: POS,
                      properties: Map<String, MorphologyProperty> = mapOf(),
                      allowIncompleteProperties: Boolean = false): SingleMorphology {

    require(pos != POS.Num) {
      "'NUM' morphologies cannot be created with the factory because they have an adding 'numericForm' property."
    }

    val kClass: KClass<*> = morphologyClasses.getValue(pos)
    val constructor: KFunction<Any> = kClass.constructors.last()

    val keywordArgs: Map<KParameter, Any?> = mapOf(
      *constructor.parameters
        .mapNotNull {
          val propertyName: String = it.name!!
          when {
            propertyName == "lemma" -> it to lemma
            propertyName in properties -> it to properties.getValue(propertyName)
            allowIncompleteProperties -> null
            else -> throw MissingMorphologyProperty(propertyName = propertyName, pos = pos, lemma = lemma)
          }
        }
        .toTypedArray()
    )

    return constructor.callBy(keywordArgs) as SingleMorphology
  }
}
