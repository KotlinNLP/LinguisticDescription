/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.properties.Person
import com.kotlinnlp.linguisticdescription.morphology.properties.Number as LDNumber
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*
import kotlin.reflect.KClass
import kotlin.reflect.full.*

/**
 * The base interface implemented by all morphologies of single lemmas.
 *
 * @property lemma the lemma
 */
abstract class SingleMorphology(val lemma: String) {

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

    /**
     * @param first the first morphology
     * @param second the second morphology
     * @param weakMatch whether to allows the match between undefined properties or not (default false)
     *
     * @return 'true' whether the [first] and [second] morphologies agree morphologically
     */
    fun <X, Y>agree(first: X, second: Y, weakMatch: Boolean = false): Boolean
      where X : Genderable,
            X : PersonDeclinable,
            X : Numerable,
            Y : Genderable,
            Y : PersonDeclinable,
            Y : Numerable{

      return if (weakMatch) {
        ((first.gender == second.gender || first.gender == Gender.Undefined || second.gender == Gender.Undefined)
          && (first.person == second.person || first.person == Person.Undefined || second.person == Person.Undefined)
          && (first.number == second.number || first.number == LDNumber.Undefined || second.number == LDNumber.Undefined))
      } else {
        (first.gender == second.gender
          && first.person == second.person
          && first.number == second.number)
      }
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
      .filter { it.type.isSubtypeOf(MorphologyProperty::class.starProjectedType) }
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

    return "`%s`: %s%s%s".format(
      this.lemma,
      this.getSuperClassesNames().joinToString(separator = "."),
      if (this is Number) " ${this.numericForm}" else "",
      if (properties.isNotEmpty()) " (" + properties.joinToString { "${it.key}: ${it.value}" } + ")" else ""
    )
  }

  override fun hashCode(): Int = this.toString().hashCode()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as SingleMorphology

    if (this.toString() != other.toString()) return false

    return true
  }

  /**
   * @return the list of names of super classes of this morphology, from the highest to itself
   */
  private fun getSuperClassesNames(): List<String> {

    val classes = mutableListOf<String>()
    var currentClass: KClass<*> = this::class

    while (currentClass != SingleMorphology::class) {
      classes.add(0, currentClass.simpleName!!)
      currentClass = (currentClass.java.superclass as Class<*>).kotlin
    }

    return classes.toList()
  }

  /**
   * @return the JSON object that represents this single morphology
   */
  fun toJSON(): JsonObject {

    val jsonObject = json {
      obj(
        "lemma" to this@SingleMorphology.lemma,
        "pos" to this@SingleMorphology.type.toString()
      )
    }

    if (this is Genderable) jsonObject["gender"] = this.gender.toString()
    if (this is Numerable) jsonObject["number"] = this.number.toString()
    if (this is PersonDeclinable) jsonObject["number"] = this.person.toString()
    if (this is CaseDeclinable) jsonObject["case"] = this.case.toString()
    if (this is Gradable) jsonObject["degree"] = this.degree.toString()
    if (this is Conjugable) {
      jsonObject["mood"] = this.mood.toString()
      jsonObject["tense"] = this.tense.toString()
    }

    return jsonObject
  }
}
