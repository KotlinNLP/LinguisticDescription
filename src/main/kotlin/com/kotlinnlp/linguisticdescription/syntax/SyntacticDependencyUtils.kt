/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

import com.kotlinnlp.linguisticdescription.syntax.dependencies.*
import kotlin.reflect.KClass

/**
 * The map of syntactic dependency k-classes associated by syntactic type.
 */
internal val syntacticDependencyClasses = mapOf<SyntacticType, KClass<*>>(

  SyntacticType.Top to Top::class,
  SyntacticType.Initiator to Initiator::class,
  SyntacticType.Separator to Separator::class,

  SyntacticType.Subject to Subject.Base::class,
  SyntacticType.InterrogativeSubject to Subject.Interrogative::class,
  SyntacticType.PassiveSubject to Subject.Passive::class,
  SyntacticType.Object to Object.Base::class,
  SyntacticType.InterrogativeObject to Object.Interrogative::class,
  SyntacticType.IndirectObject to IndirectObject.Base::class,
  SyntacticType.InterrogativeIndirectObject to IndirectObject.Interrogative::class,
//  SyntaxType.IndirectComplement to , TODO: add related class

  SyntacticType.PredCompl to PredCompl.Base::class,
  SyntacticType.PredComplSubj to PredCompl.Subj.Base::class,
  SyntacticType.PredComplObj to PredCompl.Obj.Base::class,
  SyntacticType.PredComplSubjInterr to PredCompl.Subj.Interrogative::class,
  SyntacticType.PredComplObjInterr to PredCompl.Obj.Interrogative::class,
  SyntacticType.PredComplInterr to PredCompl.Interrogative::class,

  SyntacticType.RMod to RestrictiveModifier.Base::class,
  SyntacticType.RModInterr to RestrictiveModifier.Interrogative::class,
  SyntacticType.RModNeg to RestrictiveModifier.Negative::class,
  SyntacticType.RModPoss to RestrictiveModifier.Possessive::class,
  SyntacticType.RModCompar to RestrictiveModifier.Comparative::class,
  SyntacticType.RModQuant to RestrictiveModifier.Quantitative::class,

  SyntacticType.ExtraObject to Object.Extra::class,
  SyntacticType.ExtraSubject to Subject.Extra::class,
//  SyntaxType.EmptyCompl to , TODO: add related class

  SyntacticType.Locative to Locative::class,
  SyntacticType.Partitive to Partitive::class,
  SyntacticType.Vocative to Vocative::class,

  SyntacticType.Determiner to Determiner::class,
  SyntacticType.Apposition to Apposition::class,

  SyntacticType.RelativeClause to RelativeClause.Base::class,
  SyntacticType.ReducedRelativeClause to RelativeClause.Reduced::class,

  SyntacticType.Aux to Auxiliary.Base::class,
  SyntacticType.AuxTense to Auxiliary.Tense::class,
  SyntacticType.AuxPassive to Auxiliary.Passive::class,
  SyntacticType.AuxProgressive to Auxiliary.Progressive::class,

//  SyntaxType.Coord to , TODO: add related class
//  SyntaxType.CoordAntec to , TODO: add related class
//  SyntaxType.Coord2Nd to , TODO: add related class
//  SyntaxType.Coord2NdAdvers to , TODO: add related class
//  SyntaxType.Coord2NdCompar to , TODO: add related class
//  SyntaxType.Coord2NdCorrelat to , TODO: add related class
//  SyntaxType.Coord2NdExplic to , TODO: add related class
//  SyntaxType.Coord2NdNeg to , TODO: add related class
//  SyntaxType.Coord2NdSymmetric to , TODO: add related class

  SyntacticType.Parenthetical to Parenthetical::class,
//  SyntaxType.CloseParenthetical to , TODO: add related class
//  SyntaxType.OpenParenthetical to , TODO: add related class
//  SyntaxType.CloseQuotes to , TODO: add related class
//  SyntaxType.OpenQuotes to , TODO: add related class

  SyntacticType.Connector to Connector::class,

  SyntacticType.Contin to Contin.Base::class,
  SyntacticType.ContinDenom to Contin.Denom::class,
  SyntacticType.ContinLocut to Contin.Locut::class,
  SyntacticType.ContinNumber to Contin.Number::class,
  SyntacticType.ContinCoord to Contin.Coord::class,

  SyntacticType.EndAssertive to End.Assertive::class,
  SyntacticType.EndInterrogative to End.Interrogative::class,
  SyntacticType.EndImperative to End.Imperative::class,

  SyntacticType.Interj to Interjection::class,

  SyntacticType.Unknown to Unknown::class,
  SyntacticType.Wrong to Wrong::class
)
