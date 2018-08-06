/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

/**
 * The type of a syntactic relation.
 *
 * @property annotation the string used to annotate this type
 * @property baseAnnotation the string used to annotate base label of this type
 */
enum class SyntaxType(val annotation: String, val baseAnnotation: String) {

  Top("TOP", "TOP"),
  Initiator("INITIATOR", "INITIATOR"),
  Separator("SEPARATOR", "SEPARATOR"),

  Subject("SUBJ", "SUBJ"),
  InterrogativeSubject("SUBJ-INTERR", "SUBJ"),
  PassiveSubject("SUBJ-PASS", "SUBJ"),
  Object("OBJ", "OBJ"),
  InterrogativeObject("OBJ-INTERR", "OBJ"),
  IndirectObject("IOBJ", "IOBJ"),
  InterrogativeIndirectObject("IOBJ-INTERR", "IOBJ"),
  IndirectComplement("INDCOMPL", "INDCOMPL"),

  PredCompl("PREDCOMPL", "PREDCOMPL"),
  PredComplSubj("PREDCOMPL-SUBJ", "PREDCOMPL"),
  PredComplObj("PREDCOMPL-OBJ", "PREDCOMPL"),
  PredComplSubjInterr("PREDCOMPL-SUBJ-INTERR", "PREDCOMPL"),
  PredComplObjInterr("PREDCOMPL-OBJ-INTERR", "PREDCOMPL"),
  PredComplInterr("PREDCOMPL-INTERR", "PREDCOMPL"),

  RMod("RMOD", "RMOD"),
  RModInterr("RMOD-INTERR", "RMOD"),
  RModNeg("RMOD-NEG", "RMOD"),
  RModPoss("RMOD-POSS", "RMOD"),
  RModCompar("RMOD-COMPAR", "RMOD"),
  RModQuant("RMOD-QUANT", "RMOD"),

  ExtraObject("EXTRAOBJ", "EXTRAOBJ"),
  ExtraSubject("EXTRASUBJ", "EXTRASUBJ"),
  EmptyCompl("EMPTYCOMPL", "EMPTYCOMPL"),

  Locative("LOC", "LOC"),
  Partitive("PART", "PART"),
  Vocative("VOC", "VOC"),

  Determiner("DET", "DET"),
  Apposition("APPOSITION", "APPOSITION"),

  RelativeClause("RELCL", "RELCL"),
  ReducedRelativeClause("RELCL-REDUC", "RELCL"),

  Aux("AUX", "AUX"),
  AuxTense("AUX-TENSE", "AUX"),
  AuxPassive("AUX-PASSIVE", "AUX"),
  AuxProgressive("AUX-PASSIVE", "AUX"),

  CoordAntec("COORD-ANTEC", "COORD"),
  Coord2Nd("COORD2ND", "COORD2ND"),
  Coord2NdAdvers("COORD2ND-ADVERS", "COORD2ND"),
  Coord2NdCompar("COORD2ND-COMPAR", "COORD2ND"),
  Coord2NdCorrelat("COORD2ND-CORRELAT", "COORD2ND"),
  Coord2NdExplic("COORD2ND-EXPLIC", "COORD2ND"),
  Coord2NdNeg("COORD2ND-NEG", "COORD2ND"),
  Coord2NdSymmetric("COORD2ND-SYMMETRIC", "COORD2ND"),

  Parenthetical("PARENTHETICAL", "PARENTHETICAL"),
  CloseParenthetical("CLOSE-PARENTHETICAL", "CLOSE"),
  OpenParenthetical("OPEN-PARENTHETICAL", "OPEN"),
  CloseQuotes("CLOSE-QUOTES", "CLOSE"),
  OpenQuotes("OPEN-QUOTES", "OPEN"),

  Connector("CONN", "CONN"),
  Coord("COORD", "COORD"),

  Contin("CONTIN", "CONTIN"),
  ContinDenom("CONTIN-DENOM", "CONTIN"),
  ContinLocut("CONTIN-LOCUT", "CONTIN"),
  ContinNumber("CONTIN-NUM", "CONTIN"),
  ContinCord("CONTIN-COORD", "CONTIN"),

  EndInterrogative("END-INTERROGATIVE", "END"),
  EndAssertive("END-ASSERTIVE", "END"),
  EndImperative("END-IMPERATIVE", "END"),

  Interj("INTERJ", "INTERJ"),

  Unknown("UNKNOWN", "UNKNOWN"),
  Wrong("WRONG", "WRONG")
}
