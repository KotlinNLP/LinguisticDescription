/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

/**
 * The type of a morphology.
 *
 * @property annotation the string used to annotate this type
 */
enum class MorphologyType(val annotation: String) {

  Adj("ADJ"),
  AdjCompar("ADJ-COMPAR"),
  AdjDeict("ADJ-DEITT"),
  AdjDemons("ADJ-DEMONS"),
  AdjDemonsAntec("ADJ-DEMONS-ANTEC"),
  AdjDemonsSucc("ADJ-DEMONS-SUCC"),
  AdjExclam("ADJ-EXCLAM"),
  AdjIndef("ADJ-INDEF"),
  AdjIndefSubord("ADJ-INDEF-SUBORD"),
  AdjIndefDistr("ADJ-INDEF-DISTR"),
  AdjIndefQuant("ADJ-INDEF-QUANT"),
  AdjInterr("ADJ-INTERR"),
  AdjPoss("ADJ-POSS"),
  AdjOrdin("ADJ-ORDIN"),
  AdjQualif("ADJ-QUALIF"),
  AdjQualifPost("ADJ-QUALIF-POST"),
  AdjRelat("ADJ-RELAT"),

  AdvAdvers("ADV-ADVERS"),
  AdvCompar("ADV-COMPAR"),
  AdvConcess("ADV-CONCESS"),
  AdvDeict("ADV-DEITT"),
  AdvIndef("ADV-INDEF"),
  AdvIndefSubord("ADV-INDEF-SUBORD"),
  AdvInterr("ADV-INTERR"),
  AdvLimit("ADV-LIMIT"),
  AdvLoc("ADV-LOC"),
  AdvModal("ADV"),
  AdvNeg("ADV-NEG"),
  AdvPhras("ADV-PHRAS"),
  AdvQuant("ADV-QUANT"),
  AdvStreng("ADV-STRENG"),
  AdvStrengNeg("ADV-STRENG-NEG"),
  AdvTime("ADV-TIME"),

  Art("ART"),
  ArtDef("ART-DEF"),
  ArtIndef("ART-INDEF"),
  ArtIndefPart("ART-INDEF-PART"),

  Conj("CONJ"),
  ConjCompar("CONJ-COMPAR"),
  ConjComparAntec("CONJ-COMPAR-ANTEC"),
  ConjComparSucc("CONJ-COMPAR-SUCC"),
  ConjConcess("CONJ-CONCESS"),
  ConjCoord("CONJ-COORD"),
  ConjCoordAdvers("CONJ-COORD-ADVERS"),
  ConjCoordDisj("CONJ-COORD-DISJ"),
  ConjCoordExplic("CONJ-COORD-EXPLIC"),
  ConjCoordNeg("CONJ-COORD-NEG"),
  ConjCorrel("CONJ-CORREL"),
  ConjCorrelAntec("CONJ-CORREL-ANTEC"),
  ConjCorrelSucc("CONJ-CORREL-SUCC"),
  ConjSubord("CONJ-SUBORD"),
  ConjSubordAdvers("CONJ-SUBORD-ADVERS"),
  ConjSubordInterr("CONJ-SUBORD-INTERR"),

  Noun("NOUN"),
  NounCommon("NOUN-COMMON"),
  NounCommonGerund("NOUN-COMMON-GERUND"),
  NounCommonQuant("NOUN-COMMON-QUANT"),
  NounProper("NOUN-PROPER"),
  NounProperLoc("NOUN-PROPER-LOC"),
  NounProperOrg("NOUN-PROPER-ORG"),
  NounProperPer("NOUN-PROPER-PER"),

  Pron("PRON"),
  PronDemons("PRON-DEMONS"),
  PronExclam("PRON-EXCLAM"),
  PronIndef("PRON-INDEF"),
  PronIndefDistr("PRON-INDEF-DISTR"),
  PronIndefQuant("PRON-INDEF-QUANT"),
  PronIndefSubord("PRON-INDEF-SUBORD"),
  PronInterr("PRON-INTERR"),
  PronOrdin("PRON-ORDIN"),
  PronPers("PRON-PERS"),
  PronPersEnclit("PRON-PERS-ENCLIT"),
  PronPersProclit("PRON-PERS-PROCLIT"),
  PronPersProclitRefl("PRON-PERS-PROCLIT-REFL"),
  PronPersProclitVariant("PRON-PERS-PROCLIT-VARIANT"),
  PronPersRefl("PRON-PERS-REFL"),
  PronPersVariant("PRON-PERS-VARIANT"),
  PronPoss("PRON-POSS"),
  PronRelat("PRON-RELAT"),
  PronRelatDouble("PRON-RELAT-DOUBLE"),

  Prep("PREP"),
  PrepArt("PREPART"),
  PrepPoss("PREP-POSS"),

  Postpos("POSTPOS"),
  PostposPoss("POSTPOS-POSS"),

  Interj("INTERJ"),
  Phras("PHRAS"),
  PhrasAff("PHRAS-AFF"), // new
  PhrasExclam("PHRAS-EXCLAM"),
  PhrasInterr("PHRAS-INTERR"), // new
  PhrasNeg("PHRAS-NEG"), // new
  Punct("PUNCT"),

  Verb("VERB"),
  VerbModal("VERB-MODAL"),
  VerbAux("VERB-AUX"),

  Date("DATE"),
  Hour("HOUR"),
  Num("NUM"),
  Predet("PREDET")
}
