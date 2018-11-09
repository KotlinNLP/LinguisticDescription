/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.semantic

/**
 * The type of a syntactic relation.
 *
 * @property annotation the string used to annotate this type
 * @property baseAnnotation the string used to annotate base label of this type
 */
enum class SemanticType(val annotation: String, val baseAnnotation: String) {

  SpaceTime("SPACETIME", "SPACETIME"),
  SpaceTimeInterval("SPACETIME-END", "SPACETIME"),
  SpaceTimeStart("SPACETIME-START", "SPACETIME"),
  SpaceTimeEnd("SPACETIME-END", "SPACETIME"),

  Temporal("TEMP", "TEMP"),
  TemporalStart("TEMP-START", "TEMP"),
  TemporalEnd("TEMP-END", "TEMP"),
  TemporalPrev("TEMP-PREV", "TEMP"),
  TemporalPost("TEMP-POST", "TEMP"),
  TemporalProximity("TEMP-PROXIM", "TEMP"),

  Location("LOC", "LOC"),
  LocationInside("LOC-IN", "LOC"),
  LocationDestination("LOC-DEST", "LOC"),
  LocationDistant("LOC-DIST", "LOC"),
  LocationUp("LOC-UP", "LOC"),
  LocationDown("LOC-DOWN", "LOC"),
  LocationAround("LOC-AROUND", "LOC"),
  LocationSource("LOC-SOURCE", "LOC"),
  LocationProximity("LOC-PROXIM", "LOC"),
  LocationContact("LOC-CONTACT", "LOC"),
  LocationSide("LOC-SIDE", "LOC"),
  LocationSideRight("LOC-SIDE-RIGHT", "LOC"),
  LocationSideLeft("LOC-SIDE-LEFT", "LOC"),

  Mov("MOV", "MOV"),
  MovIn("MOV-IN", "MOV"),
  MovFrom("MOV-FROM", "MOV"),
  MovTo("MOV-TO", "MOV"),
  MovTrough("MOV-TROUGH", "MOV"),

  Comparative("COMPAR", "COMPAR"),
  ComparativeLimitative("COMPAR-LIMIT", "COMPAR"),
  ComparativeAccrescitive("COMPAR-ACC", "COMPAR"),
  ComparativeEqual("COMPAR-EQUAL", "COMPAR"),
  ComparativeEqualNegation("COMPAR-EQUAL-NEG", "COMPAR"),

  Manner("MANNER", "MANNER"),
  MannerEqual("MANNER-EQUAL", "MANNER"),
  MannerUnequal("MANNER-UNEQUAL", "MANNER"),

  Modal("MODAL", "MODAL"),
  Conditional("COND", "COND"),
  Concessive("CONC", "CONC"),
  Causal("CAUS", "CAUS"),
  Reason("REASON", "REASON"),
  Reasoncause("REASONCAUSE", "REASONCAUSE"),
  Advers("ADVERS", "ADVERS"),
  Limitative("LIMIT", "LIMIT"),
  Goal("GOAL", "GOAL"),
  Exceptive("EXCEPT", "EXCEPT"),
  Explicative("EXPLIC", "EXPLIC"),
  Theme("THEME", "THEME"),
  Progress("PROGRESS", "PROGRESS"),
  Union("UNION", "UNION"),
  Disunion("DISUNION", "DISUNION"),
  Concerns("CONCERNS", "CONCERNS"),
  Partitive("PARTITIVE", "PARTITIVE"),

  Specification("SPEC", "SPEC"),
  SpecificationDenominative("SPEC-DENOM", "SPEC"),

  Companionship("COMPANIONSHIP", "COMPANIONSHIP"),
  CompanionshipFigurative("COMPANIONSHIP-FIG", "COMPANIONSHIP"),

  Beneficiary("BENEFICIARY", "BENEFICIARY"),
  BeneficiaryDisvantage("BENEFICIARY-DIS", "BENEFICIARY"),

  Means("MEANS", "MEANS"),
  Instrument("INSTRUMENT", "INSTRUMENT"),
  Material("MATERIAL", "MATERIAL"),
  Fault("FAULT", "FAULT"),
  Penalty("PENALTY", "PENALTY"),
  Replacement("REPLACEMENT", "REPLACEMENT");

  /*
    TODO:
    "CONS"
    "ECC"
    "BETWEEN"
   */

  /**
   * The list of components.
   */
  val components: List<SemanticType> by lazy {
    val components: List<String> = this.annotation.split(COMPONENTS_SEP)
    components.indices.map { i ->
      SemanticType.byAnnotation(components.subList(0, i + 1).joinToString(COMPONENTS_SEP))
    }
  }

  /**
   * @param component a component of this syntactic type
   *
   * @return true if any component of this syntactic type is equal to the given one, otherwise false
   */
  fun isComposedBy(component: SemanticType): Boolean = this.components.any { it == component }

  /**
   * Factory object.
   */
  companion object Factory {

    /**
     * The separator of components in the annotation string.
     */
    private const val COMPONENTS_SEP = "-"

    /**
     * Raised when trying to build a [SemanticType] through an invalid annotation.
     */
    class InvalidAnnotation(annotation: String) : RuntimeException(annotation)

    /**
     * Syntactic types associated by base annotation.
     */
    private val baseTypesMap: Map<String, SemanticType> =
      SemanticType.values().filter { it.annotation == it.baseAnnotation }.associateBy { it.baseAnnotation }

    /**
     * Syntactic types associated by annotation.
     */
    private val typeMap: Map<String, SemanticType> =
      SemanticType.values().associateBy { it.annotation }

    /**
     * @param annotation a syntactic type base annotation
     *
     * @throws InvalidAnnotation when the annotation is not valid
     *
     * @return the base syntactic type with the given annotation
     */
    fun byBaseAnnotation(annotation: String): SemanticType =
      this.baseTypesMap[annotation] ?: throw InvalidAnnotation(annotation)

    /**
     * @param annotation a syntactic type annotation
     *
     * @throws InvalidAnnotation when the annotation is not valid
     *
     * @return the syntactic type with the given annotation
     */
    fun byAnnotation(annotation: String): SemanticType =
      this.typeMap[annotation] ?: throw InvalidAnnotation(annotation)
  }
}
