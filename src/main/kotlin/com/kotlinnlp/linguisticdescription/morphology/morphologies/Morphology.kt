/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * The base interface implemented by all morphologies.
 *
 * @property lemma the lemma
 */
abstract class Morphology(val lemma: String) {

  /**
   * The type associated to this morphology.
   */
  abstract val type: MorphologyType

  /**
   * @return a map of properties names to values
   */
  fun getProperties(): Map<String, MorphologyProperty> {

    val paramsNames: Set<String> = this::class.primaryConstructor!!.parameters
      .filter { it.name!! != "lemma" }
      .map { it.name!! }
      .toSet()

    return this::class.memberProperties
      .filter { it.name in paramsNames }
      .associate { it.name to it.getter.call(this) as MorphologyProperty }
  }

  /**
   * @return the string representation of this morphology (with the values of its properties)
   */
  override fun toString() = "%s (%s)".format(
    this.getSuperClassesNames().joinToString(separator = "."),
    this.getProperties().entries.joinToString { "${it.key}: ${it.value}" }
  )

  /**
   * @return the list of names of super classes of this morphology, from the highest to itself
   */
  private fun getSuperClassesNames(): List<String> {

    val classes = mutableListOf<String>()
    var currentClass: KClass<*> = this::class

    while (currentClass != Morphology::class) {
        classes.add(0, currentClass.simpleName!!)
        currentClass = (currentClass.java.superclass as Class<*>).kotlin
    }

    return classes.toList()
  }
}
