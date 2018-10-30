/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.kotlinnlp.linguisticdescription.MissingMorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.Gender
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.properties.Person
import com.kotlinnlp.linguisticdescription.morphology.properties.Number as LDNumber
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.*
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.*

/**
 * The base interface implemented by all morphologies of single lemmas.
 *
 * @property lemma the lemma
 */
abstract class SingleMorphology(val lemma: String) {

  companion object {

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

    /**
     * Get the list of properties required to build a certain morphology.
     *
     * @param pos a POS
     *
     * @return a list of required properties for a morphology with the given pos tag
     */
    fun getRequiredProperties(pos: POS): List<String> {

      val requiredProperties = mutableListOf<String>()
      val morphologyClass: KClass<*> = morphologyClasses.getValue(pos)

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
   * The POS of this morphology.
   */
  abstract val pos: POS

  /**
   * @param other another morphology
   * @param weakMatch whether to allows the match between undefined properties or not (default false)
   *
   * @return true if this morphology and [other] morphologies agree morphologically, otherwise false
   */
  fun agree(other: SingleMorphology, weakMatch: Boolean = false): Boolean {

    return if (this is Genderable && this is Numerable && this is PersonDeclinable
      && other is Genderable && other is Numerable && other is PersonDeclinable)

      when {
        weakMatch ->
          (this.gender == other.gender || this.gender == Gender.Undefined || other.gender == Gender.Undefined)
            && (this.person == other.person || this.person == Person.Undefined || other.person == Person.Undefined)
            && (this.number == other.number || this.number == LDNumber.Undefined || other.number == LDNumber.Undefined)

        else ->
          this.gender == other.gender
            && this.person == other.person
            && this.number == other.number
      }
    else
      false
  }

  /**
   * @param other another morphology
   *
   * @return true if the gender properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInGender(other: SingleMorphology): Boolean =
    this is Genderable && other is Genderable && this.gender == other.gender

  /**
   * @param other another morphology
   *
   * @return true if the number properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInNumber(other: SingleMorphology): Boolean =
    this is Numerable && other is Numerable && this.number == other.number

  /**
   * @param other another morphology
   *
   * @return true if the person properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInPerson(other: SingleMorphology): Boolean =
    this is PersonDeclinable && other is PersonDeclinable && this.person == other.person

  /**
   * @param other another morphology
   *
   * @return true if the case properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInCase(other: SingleMorphology): Boolean =
    this is CaseDeclinable && other is CaseDeclinable && this.case == other.case

  /**
   * @param other another morphology
   *
   * @return true if the degree properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInDegree(other: SingleMorphology): Boolean =
    this is Gradable && other is Gradable && this.degree == other.degree

  /**
   * @param other another morphology
   *
   * @return true if the mood properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInMood(other: SingleMorphology): Boolean =
    this is Conjugable && other is Conjugable && this.mood == other.mood

  /**
   * @param other another morphology
   *
   * @return true if the tense properties of this morphology and the [other] are equal, otherwise false
   */
  fun agreeInTense(other: SingleMorphology): Boolean =
    this is Conjugable && other is Conjugable && this.tense == other.tense

  /**
   * @return a map of properties names to values
   */
  fun getProperties(): Map<String, MorphologyProperty> {

    val paramsNames: Set<String> = this::class.primaryConstructor!!.parameters
      .asSequence()
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
      if (this is Number && this.numericForm != null) " ${this.numericForm}" else "",
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
        "pos" to this@SingleMorphology.pos.toString()
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
