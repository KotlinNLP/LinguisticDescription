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
 * The map of syntax dependency k-classes associated by syntax type.
 */
internal val syntaxDependencyClasses = mapOf<SyntaxType, KClass<*>>(

  SyntaxType.Top to Top::class,
  SyntaxType.Initiator to Initiator::class,
  SyntaxType.Separator to Separator::class,

  SyntaxType.Subject to Subject.Base::class,
  SyntaxType.InterrogativeSubject to Subject.Interrogative::class,
  SyntaxType.PassiveSubject to Subject.Passive::class,
  SyntaxType.Object to Object.Base::class,
  SyntaxType.InterrogativeObject to Object.Interrogative::class,
  SyntaxType.IndirectObject to IndirectObject.Base::class,
  SyntaxType.InterrogativeIndirectObject to IndirectObject.Interrogative::class,
//  SyntaxType.IndirectComplement to , TODO: add related class

  SyntaxType.PredCompl to PredCompl.Base::class,
  SyntaxType.PredComplSubj to PredCompl.Subj.Base::class,
  SyntaxType.PredComplObj to PredCompl.Obj.Base::class,
  SyntaxType.PredComplSubjInterr to PredCompl.Subj.Interrogative::class,
  SyntaxType.PredComplObjInterr to PredCompl.Obj.Interrogative::class,
  SyntaxType.PredComplInterr to PredCompl.Interrogative::class,

  SyntaxType.RMod to RestrictiveModifier.Base::class,
  SyntaxType.RModInterr to RestrictiveModifier.Interrogative::class,
  SyntaxType.RModNeg to RestrictiveModifier.Negative::class,
  SyntaxType.RModPoss to RestrictiveModifier.Possessive::class,
  SyntaxType.RModCompar to RestrictiveModifier.Comparative::class,
  SyntaxType.RModQuant to RestrictiveModifier.Quantitative::class,

  SyntaxType.ExtraObject to Object.Extra::class,
  SyntaxType.ExtraSubject to Subject.Extra::class,
//  SyntaxType.EmptyCompl to , TODO: add related class

  SyntaxType.Locative to Locative::class,
  SyntaxType.Partitive to Partitive::class,
  SyntaxType.Vocative to Vocative::class,

  SyntaxType.Determiner to Determiner::class,
  SyntaxType.Apposition to Apposition::class,

  SyntaxType.RelativeClause to RelativeClause.Base::class,
  SyntaxType.ReducedRelativeClause to RelativeClause.Reduced::class,

  SyntaxType.Aux to Auxiliary.Base::class,
  SyntaxType.AuxTense to Auxiliary.Tense::class,
  SyntaxType.AuxPassive to Auxiliary.Passive::class,
  SyntaxType.AuxProgressive to Auxiliary.Progressive::class,

//  SyntaxType.Coord to , TODO: add related class
//  SyntaxType.CoordAntec to , TODO: add related class
//  SyntaxType.Coord2Nd to , TODO: add related class
//  SyntaxType.Coord2NdAdvers to , TODO: add related class
//  SyntaxType.Coord2NdCompar to , TODO: add related class
//  SyntaxType.Coord2NdCorrelat to , TODO: add related class
//  SyntaxType.Coord2NdExplic to , TODO: add related class
//  SyntaxType.Coord2NdNeg to , TODO: add related class
//  SyntaxType.Coord2NdSymmetric to , TODO: add related class

  SyntaxType.Parenthetical to Parenthetical::class,
//  SyntaxType.CloseParenthetical to , TODO: add related class
//  SyntaxType.OpenParenthetical to , TODO: add related class
//  SyntaxType.CloseQuotes to , TODO: add related class
//  SyntaxType.OpenQuotes to , TODO: add related class

  SyntaxType.Connector to Connector::class,

  SyntaxType.Contin to Contin.Base::class,
  SyntaxType.ContinDenom to Contin.Denom::class,
  SyntaxType.ContinLocut to Contin.Locut::class,
  SyntaxType.ContinNumber to Contin.Number::class,
  SyntaxType.ContinCoord to Contin.Coord::class,

  SyntaxType.EndAssertive to End.Assertive::class,
  SyntaxType.EndInterrogative to End.Interrogative::class,
  SyntaxType.EndImperative to End.Imperative::class,

  SyntaxType.Interj to Interjection::class,

  SyntaxType.Unknown to Unknown::class,
  SyntaxType.Wrong to Wrong::class
)
