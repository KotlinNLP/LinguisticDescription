/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse.Discourse
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.Relation
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Thing
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * The Part-Of-Speech.
 *
 * @property annotation the string used to annotate this POS
 * @property baseAnnotation the base level of annotation
 */
enum class POS(val annotation: String, val baseAnnotation: String) {

  Adj("ADJ", "ADJ"),
  AdjCompar("ADJ-COMPAR", "ADJ"),
  AdjDeict("ADJ-DEITT", "ADJ"),
  AdjDemons("ADJ-DEMONS", "ADJ"),
  AdjDemonsAntec("ADJ-DEMONS-ANTEC", "ADJ"),
  AdjDemonsSucc("ADJ-DEMONS-SUCC", "ADJ"),
  AdjExclam("ADJ-EXCLAM", "ADJ"),
  AdjIndef("ADJ-INDEF", "ADJ"),
  AdjIndefSubord("ADJ-INDEF-SUBORD", "ADJ"),
  AdjIndefDistr("ADJ-INDEF-DISTR", "ADJ"),
  AdjIndefQuant("ADJ-INDEF-QUANT", "ADJ"),
  AdjInterr("ADJ-INTERR", "ADJ"),
  AdjPoss("ADJ-POSS", "ADJ"),
  AdjOrdin("ADJ-ORDIN", "ADJ"),
  AdjQualif("ADJ-QUALIF", "ADJ"),
  AdjQualifPost("ADJ-QUALIF-POST", "ADJ"),
  AdjRelat("ADJ-RELAT", "ADJ"),

  AdvModal("ADV", "ADV"),
  AdvAdvers("ADV-ADVERS", "ADV"),
  AdvCompar("ADV-COMPAR", "ADV"),
  AdvConcess("ADV-CONCESS", "ADV"),
  AdvDeict("ADV-DEITT", "ADV"),
  AdvIndef("ADV-INDEF", "ADV"),
  AdvIndefSubord("ADV-INDEF-SUBORD", "ADV"),
  AdvInterr("ADV-INTERR", "ADV"),
  AdvLimit("ADV-LIMIT", "ADV"),
  AdvLoc("ADV-LOC", "ADV"),
  AdvNeg("ADV-NEG", "ADV"),
  AdvPhras("ADV-PHRAS", "ADV"),
  AdvQuant("ADV-QUANT", "ADV"),
  AdvStreng("ADV-STRENG", "ADV"),
  AdvStrengNeg("ADV-STRENG-NEG", "ADV"),
  AdvTime("ADV-TIME", "ADV"),

  Art("ART", "ART"),
  ArtDef("ART-DEF", "ART"),
  ArtIndef("ART-INDEF", "ART"),
  ArtIndefPart("ART-INDEF-PART", "ART"),

  Conj("CONJ", "CONJ"),
  ConjCompar("CONJ-COMPAR", "CONJ"),
  ConjComparAntec("CONJ-COMPAR-ANTEC", "CONJ"),
  ConjComparSucc("CONJ-COMPAR-SUCC", "CONJ"),
  ConjConcess("CONJ-CONCESS", "CONJ"),
  ConjCoord("CONJ-COORD", "CONJ"),
  ConjCoordAdvers("CONJ-COORD-ADVERS", "CONJ"),
  ConjCoordDisj("CONJ-COORD-DISJ", "CONJ"),
  ConjCoordExplic("CONJ-COORD-EXPLIC", "CONJ"),
  ConjCoordNeg("CONJ-COORD-NEG", "CONJ"),
  ConjCorrel("CONJ-CORREL", "CONJ"),
  ConjCorrelAntec("CONJ-CORREL-ANTEC", "CONJ"),
  ConjCorrelSucc("CONJ-CORREL-SUCC", "CONJ"),
  ConjSubord("CONJ-SUBORD", "CONJ"),
  ConjSubordAdvers("CONJ-SUBORD-ADVERS", "CONJ"),
  ConjSubordInterr("CONJ-SUBORD-INTERR", "CONJ"),

  Noun("NOUN", "NOUN"),
  NounCommon("NOUN-COMMON", "NOUN"),
  NounCommonGerund("NOUN-COMMON-GERUND", "NOUN"),
  NounCommonQuant("NOUN-COMMON-QUANT", "NOUN"),
  NounProper("NOUN-PROPER", "NOUN"),
  NounProperLoc("NOUN-PROPER-LOC", "NOUN"),
  NounProperOrg("NOUN-PROPER-ORG", "NOUN"),
  NounProperPer("NOUN-PROPER-PER", "NOUN"),

  Pron("PRON", "PRON"),
  PronDemons("PRON-DEMONS", "PRON"),
  PronExclam("PRON-EXCLAM", "PRON"),
  PronIndef("PRON-INDEF", "PRON"),
  PronIndefDistr("PRON-INDEF-DISTR", "PRON"),
  PronIndefQuant("PRON-INDEF-QUANT", "PRON"),
  PronIndefSubord("PRON-INDEF-SUBORD", "PRON"),
  PronInterr("PRON-INTERR", "PRON"),
  PronOrdin("PRON-ORDIN", "PRON"),
  PronPers("PRON-PERS", "PRON"),
  PronPersEnclit("PRON-PERS-ENCLIT", "PRON"),
  PronPersProclit("PRON-PERS-PROCLIT", "PRON"),
  PronPersProclitRefl("PRON-PERS-PROCLIT-REFL", "PRON"),
  PronPersProclitVariant("PRON-PERS-PROCLIT-VARIANT", "PRON"),
  PronPersRefl("PRON-PERS-REFL", "PRON"),
  PronPersVariant("PRON-PERS-VARIANT", "PRON"),
  PronPoss("PRON-POSS", "PRON"),
  PronRelat("PRON-RELAT", "PRON"),
  PronRelatDouble("PRON-RELAT-DOUBLE", "PRON"),

  Prep("PREP", "PREP"),
  PrepArt("PREP-ART", "PREP"),
  PrepPoss("PREP-POSS", "PREP"),

  Postpos("POSTPOS", "POSTPOS"),
  PostposPoss("POSTPOS-POSS", "POSTPOS"),

  Interj("INTERJ", "INTERJ"),
  PhrasAff("PHRASE", "PHRASE"), // new
  PhrasExclam("PHRASE-EXCLAM", "PHRASE"),
  PhrasInterr("PHRASE-INTERR", "PHRASE"), // new
  PhrasNeg("PHRASE-NEG", "PHRASE"), // new
  Punct("PUNCT", "PUNCT"),

  Verb("VERB", "VERB"),
  VerbModal("VERB-MODAL", "VERB"),
  VerbAux("VERB-AUX", "VERB"),

  Date("DATE", "DATE"),
  Hour("HOUR", "HOUR"),
  Num("NUM", "NUM"),
  Predet("PREDET", "PREDET");

  /**
   * The list of components.
   */
  val components: List<POS> by lazy {
    val components: List<String> = this.annotation.split(COMPONENTS_SEP)
    components.indices.map { i ->
      POS.byAnnotation(components.subList(0, i + 1).joinToString(COMPONENTS_SEP))
    }
  }

  /**
   * Whether this POS is a [ContentWord].
   */
  val isContentWord: Boolean by lazy { morphologyClasses.getValue(this).isSubclassOf(ContentWord::class) }

  /**
   * Whether this POS is a [Discourse].
   */
  val isDiscourse: Boolean by lazy { morphologyClasses.getValue(this).isSubclassOf(Discourse::class) }

  /**
   * Whether this POS is a [Relation].
   */
  val isRelation: Boolean by lazy { morphologyClasses.getValue(this).isSubclassOf(Relation::class) }

  /**
   * Whether this POS is a [Thing].
   */
  val isThing: Boolean by lazy { morphologyClasses.getValue(this).isSubclassOf(Thing::class) }

  /**
   * @param morphologyClass the KClass of a single morphology
   *
   * @return true if this POS is a subtype of the type of given morphology, otherwise false
   */
  fun <T : SingleMorphology>isSubTypeOf(morphologyClass: KClass<T>): Boolean =
    morphologyClasses.getValue(this).isSubclassOf(morphologyClass)

  /**
   * Factory object.
   */
  companion object Factory {

    /**
     * Raised when trying to build a [POS] through an invalid annotation.
     */
    class InvalidAnnotation(annotation: String) : RuntimeException(annotation)

    /**
     * The separator of components in the annotation string.
     */
    private const val COMPONENTS_SEP = "-"

    /**
     * POS associated by base annotation.
     */
    private val basePOSMap: Map<String, POS> =
      POS.values().filter { it.annotation == it.baseAnnotation }.associateBy { it.baseAnnotation }

    /**
     * POS associated by annotation.
     */
    private val posMap: Map<String, POS> =
      POS.values().associateBy { it.annotation }

    /**
     * @param annotation a POS base annotation
     *
     * @throws InvalidAnnotation when the annotation is not valid
     *
     * @return the base POS with the given annotation
     */
    fun byBaseAnnotation(annotation: String): POS = this.basePOSMap[annotation] ?: throw InvalidAnnotation(annotation)

    /**
     * @param annotation a POS annotation
     *
     * @throws InvalidAnnotation when the annotation is not valid
     *
     * @return the POS with the given annotation
     */
    fun byAnnotation(annotation: String): POS = this.posMap[annotation] ?: throw InvalidAnnotation(annotation)
  }
}
