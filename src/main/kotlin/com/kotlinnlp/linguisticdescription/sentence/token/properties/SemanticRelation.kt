/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

/**
 * A semantic relation.
 *
 * @property type the type of relation
 * @property annotation the string used to annotate this relation
 */
enum class SemanticRelation(val type: String, val annotation: String) {

  Space("Location / Time", "SPACE"),
  SpaceInterval("Location / Time", "SPACE-INTERVAL"),
  SpaceStart("Location / Time", "SPACE-START"),
  SpaceEnd("Location / Time", "SPACE-END"),

  Temp("Temporal", "TEMP"),
  TempStart("Temporal", "TEMP-START"),
  TempEnd("Temporal", "TEMP-END"),
  TempPrev("Temporal", "TEMP-PREV"),
  TempPost("Temporal", "TEMP-POST"),
  TempProxim("Temporal", "TEMP-PROXIM"),

  Loc("Location", "LOC"),
  LocIn("Location", "LOC-IN"),
  LocDest("Location", "LOC-DEST"),
  LocDist("Location", "LOC-DIST"),
  LocUp("Location", "LOC-UP"),
  LocDown("Location", "LOC-DOWN"),
  LocAround("Location", "LOC-AROUND"),
  LocSource("Location", "LOC-SOURCE"),
  LocProxim("Location", "LOC-PROXIM"),
  LocContact("Location", "LOC-CONTACT"),
  LocSide("Location", "LOC-SIDE"),
  LocSideRight("Location", "LOC-SIDE-RIGHT"),
  LocSideLeft("Location", "LOC-SIDE-LEFT"),

  Movement("Movement", "MOV"),
  MovementInPlace("Movement", "MOV-IN-PLACE"),
  MovementFromPlace("Movement", "MOV-FROM-PLACE"),
  MovementToPlace("Movement", "MOV-TO-PLACE"),
  MovementTroughPlace("Movement", "MOV-TROUGH-PLACE"),

  Caus("Others", "CAUS"),
  Modal("Others", "MODAL"),
  Manner("Others", "MANNER"),
  MannerEqual("Others", "MANNER-EQUAL"),
  MannerEqualNeg("Others", "MANNER-EQUAL-NEG"),
  Cond("Others", "COND"),
  Conc("Others", "CONC"),
  Reason("Others", "REASON"),
  ReasonCause("Others", "REASONCAUSE"),
  Compar("Others", "COMPAR"),
  ComparEqual("Others", "COMPAR-EQUAL"),
  ComparEqualNeg("Others", "COMPAR-EQUAL-NEG"),
  ComparLimit("Others", "COMPAR-LIMIT"),
  Goal("Others", "GOAL"),
  Union("Others", "UNION"),
  Disunion("Others", "DISUNION"),
  Limit("Others", "LIMIT"),
  Concerns("Others", "CONCERNS"),
  Explic("Others", "EXPLIC"),
  Advers("Others", "ADVERS"),

  Theme("Others", "THEME"),
  Except("Others", "EXCEPT"),
  Cons("Others", "CONS"),
  Ecc("Others", "ECC"),

  Between("Others", "BETWEEN"),
  Replacement("Others", "REPLACEMENT"),
  Favourable("Others", "FAVOURABLE"),
  FavourableNeg("Others", "FAVOURABLE-NEG"),
  Beneficiary("Others", "BENEFICIARY"),
  BeneficiaryNeg("Others", "BENEFICIARY-NEG"),
  Progress("Others", "PROGRESS"),
  Instrumental("Others", "INSTRUMENTAL"),
  Fault("Others", "FAULT"),
  Penalty("Others", "PENALTY"),
  Material("Others", "MATERIAL"),
  Partitive("Others", "PARTITIVE"),
  Means("Others", "MEANS"),
  Spec("Others", "SPEC"),
  SpecDenom("Others", "SPEC-DENOM"),
  Companionship("Others", "COMPANIONSHIP"),
  CompanionshipFigurative("Others", "COMPANIONSHIP-FIGURATIVE")
}

