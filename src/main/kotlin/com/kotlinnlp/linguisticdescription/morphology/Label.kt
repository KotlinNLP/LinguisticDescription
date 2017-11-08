/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

/**
 * The label associated to each morphology.
 *
 * @property annotation the related label in the annotated files
 */
enum class Label(val annotation: String) {

  Verb("VERB"),
  VerbModal("VERB-MODAL"),
  VerbAux("VERB-AUX"),

  Adj("ADJ"),
  AdjDeitt("ADJ-DEITT"),
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
  AdjCompar("ADJ-COMPAR"),
  AdjQualif("ADJ-QUALIF"),
  AdjQualifPost("ADJ-QUALIF-POST"),
  AdjRelat("ADJ-RELAT"),

  Adv("ADV"),
  AdvAdvers("ADV-ADVERS"),
  AdvCompar("ADV-COMPAR"),
  AdvIndef("ADV-INDEF"),
  AdvIndefSubord("ADV-INDEF-SUBORD"),
  AdvInterr("ADV-INTERR"),
  AdvLimit("ADV-LIMIT"),
  AdvLoc("ADV-LOC"),
  AdvNeg("ADV-NEG"),
  AdvPhras("ADV-PHRAS"),
  AdvQuant("ADV-QUANT"),
  AdvStreng("ADV-STRENG"),
  AdvStrengNeg("ADV-STRENG-NEG"),
  AdvTime("ADV-TIME"),
  AdvConcess("ADV-CONCESS"),
  AdvDeitt("ADV-DEITT"),

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
  NounCommonQuant("NOUN-COMMON-QUANT"),
  NounProper("NOUN-PROPER"),
  NounProperPer("NOUN-PROPER-PER"),
  NounProperOrg("NOUN-PROPER-ORG"),
  NounProperLoc("NOUN-PROPER-LOC"),
  NounCommonGerund("NOUN-COMMON-GERUND"),

  Pron("PRON"),
  PronExclam("PRON-EXCLAM"),
  PronOrdin("PRON-ORDIN"),
  PronDemons("PRON-DEMONS"),
  PronIndef("PRON-INDEF"),
  PronIndefSubord("PRON-INDEF-SUBORD"),
  PronIndefDistr("PRON-INDEF-DISTR"),
  PronIndefQuant("PRON-INDEF-QUANT"),
  PronInterr("PRON-INTERR"),
  PronPers("PRON-PERS"),
  PronPersVariant("PRON-PERS-VARIANT"),
  PronPersRefl("PRON-PERS-REFL"),
  PronPersEnclit("PRON-PERS-ENCLIT"),
  PronPersProclit("PRON-PERS-PROCLIT"),
  PronPersProclitRefl("PRON-PERS-PROCLIT-REFL"),
  PronPersProclitVariant("PRON-PERS-PROCLIT-VARIANT"),
  PronPoss("PRON-POSS"),
  PronRelat("PRON-RELAT"),
  PronRelatDouble("PRON-RELAT-DOUBLE"),

  Prep("PREP"),
  PrepMono("PREP-MONO"),
  PrepMonoVariant("PREP-MONO-VARIANT"),
  PrepPoss("PREP-POSS"),
  PrepArt("PREPART"),

  Postpos("POSTPOS"),
  PostposPoss("POSTPOS-POSS"),

  Date("DATE"),
  Hour("HOUR"),
  Interj("INTERJ"),
  Num("NUM"),
  Phras("PHRAS"),
  PhrasExclam("PHRAS-EXCLAM"),
  Predet("PREDET"),
  Punct("PUNCT")
}
