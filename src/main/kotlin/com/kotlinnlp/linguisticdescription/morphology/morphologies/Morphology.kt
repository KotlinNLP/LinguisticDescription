/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * The base interface implemented by all morphologies.
 *
 * @property lemma the lemma
 */
abstract class Morphology(val lemma: String) {

  companion object {

    /**
     * Get the list of properties required to build a certain morphology.
     *
     * @param type a morphology type
     *
     * @return a list of required properties for a morphology of the given type
     */
    fun getRequiredProperties(type: MorphologyType): List<String> {

      val requiredProperties = mutableListOf<String>()
      val morphologyClass: KClass<*> = morphologyClasses.getValue(type)

      if (morphologyClass.isSubclassOf(Genderable::class)) requiredProperties.add("gender")
      if (morphologyClass.isSubclassOf(Numerable::class)) requiredProperties.add("number")
      if (morphologyClass.isSubclassOf(PersonDeclinable::class)) requiredProperties.add("person")
      if (morphologyClass.isSubclassOf(Gradable::class)) requiredProperties.add("degree")
      if (morphologyClass.isSubclassOf(CaseDeclinable::class)) requiredProperties.add("case")
      if (morphologyClass.isSubclassOf(Conjugable::class)) {
        requiredProperties.add("mood")
        requiredProperties.add("tense")
      }

      return requiredProperties
    }
  }

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
  override fun toString(): String {

    val properties = this.getProperties().entries

    return "'%s': %s%s".format(
      this.lemma,
      this.getSuperClassesNames().joinToString(separator = "."),
      if (properties.isNotEmpty()) " (" + properties.joinToString { "${it.key}: ${it.value}" } + ")" else ""
    )
  }

  override fun hashCode(): Int = this.toString().hashCode()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Morphology

    if (this.lemma != other.lemma) return false
    if (this.toString() != other.toString()) return false

    return true
  }

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
