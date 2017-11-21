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
 */
enum class SyntaxType(val annotation: String) {

  Top("TOP"),
  Initiator("INITIATOR"),
  Separator("SEPARATOR"),

  Subject("SUBJ"),
  InterrogativeSubject("SUBJ-INTERR"),
  PassiveSubject("SUBJ-PASS"),
  Object("OBJ"),
  InterrogativeObject("OBJ-INTERR"),
  IndirectObject("IOBJ"),
  IndirectComplement("INDCOMPL"),

  PredCompl("PREDCOMPL"),
  PredComplSubj("PREDCOMPL-SUBJ"),
  PredComplObj("PREDCOMPL-OBJ"),
  PredComplSubjInterr("PREDCOMPL-SUBJ-INTERR"),
  PredComplObjInterr("PREDCOMPL-OBJ-INTERR"),
  PredComplInterr("PREDCOMPL-INTERR"),

  RMod("RMOD"),
  RModInterr("RMOD-INTERR"),
  RModNeg("RMOD-NEG"),
  RModPoss("RMOD-POSS"),
  RModCompar("RMOD-COMPAR"),
  RModQuant("RMOD-QUANT"),

  ExtraObject("EXTRAOBJ"),
  ExtraSubject("EXTRASUBJ"),
  EmptyCompl("EMPTYCOMPL"),

  Locative("LOC"),
  Partitive("PART"),
  Vocative("VOC"),

  Determiner("DET"),
  Apposition("APPOSITION"),

  RelativeClause("RELCL"),
  ReducedRelativeClause("RELCL-REDUC"),

  Aux("AUX"),
  AuxTense("AUX-TENSE"),
  AuxPassive("AUX-PASSIVE"),
  AuxProgressive("AUX-PASSIVE"),

  CoordAntec("COORD-ANTEC"),
  Coord2Nd("COORD2ND"),
  Coord2NdAdvers("COORD2ND-ADVERS"),
  Coord2NdCompar("COORD2ND-COMPAR"),
  Coord2NdCorrelat("COORD2ND-CORRELAT"),
  Coord2NdExplic("COORD2ND-EXPLIC"),
  Coord2NdNeg("COORD2ND-NEG"),
  Coord2NdSymmetric("COORD2ND-SYMMETRIC"),

  Parenthetical("PARENTHETICAL"),
  CloseParenthetical("CLOSE-PARENTHETICAL"),
  OpenParenthetical("OPEN-PARENTHETICAL"),
  CloseQuotes("CLOSE-QUOTES"),
  OpenQuotes("OPEN-QUOTES"),

  Connector("CONN"),
  Coord("COORD"),

  Contin("CONTIN"),
  ContinDenom("CONTIN-DENOM"),
  ContinLocut("CONTIN-LOCUT"),
  ContinNumber("CONTIN-NUM"),
  ContinCord("CONTIN-COORD"),

  EndInterrogative("END-INTERROGATIVE"),
  EndAssertive("END-ASSERTIVE"),
  EndImperative("END-IMPERATIVE"),

  Interj("INTERJ"),

  Unknown("UNKNOWN"),
  Wrong("WRONG")
}
