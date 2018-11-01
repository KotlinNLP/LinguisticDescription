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

  SyntacticType.Subject to Subject.Base::class,
  SyntacticType.InterrogativeSubject to Subject.Interrogative::class,
  SyntacticType.PassiveSubject to Subject.Passive::class,
  SyntacticType.Object to Object.Base::class,
  SyntacticType.InterrogativeObject to Object.Interrogative::class,
  SyntacticType.IndirectObject to IndirectObject.Base::class,
  SyntacticType.InterrogativeIndirectObject to IndirectObject.Interrogative::class,
  SyntacticType.IndirectComplement to IndirectComplement::class,

  SyntacticType.PredCompl to PredCompl.Base::class,
  SyntacticType.PredComplSubj to PredCompl.Subj.Base::class,
  SyntacticType.PredComplObj to PredCompl.Obj.Base::class,
  SyntacticType.PredComplSubjInterr to PredCompl.Subj.Interrogative::class,
  SyntacticType.PredComplObjInterr to PredCompl.Obj.Interrogative::class,
  SyntacticType.PredComplInterr to PredCompl.Interrogative::class,

  SyntacticType.RMod to RestrictiveModifier.Base::class,
  SyntacticType.RModInterr to RestrictiveModifier.Interrogative::class,
  SyntacticType.RModExclamative to RestrictiveModifier.Exclamative::class,
  SyntacticType.RModNeg to RestrictiveModifier.Negative::class,
  SyntacticType.RModPoss to RestrictiveModifier.Possessive::class,
  SyntacticType.RModCompar to RestrictiveModifier.Comparative::class,
  SyntacticType.RModQuant to RestrictiveModifier.Quantitative::class,
  SyntacticType.RMod2ND to RestrictiveModifier2ND.Base::class,
  SyntacticType.RMod2NDNeg to RestrictiveModifier2ND.Negative::class,

  SyntacticType.ExtraObject to Object.Extra::class,
  SyntacticType.ExtraSubject to Subject.Extra::class,
  SyntacticType.EmptyCompl to EmptyComplement::class,

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

  SyntacticType.Coord to Coordinator.Base::class,
  SyntacticType.CoordAntecedent to Coordinator.Antecedent::class,
  SyntacticType.CoordNegative to Coordinator.Negative::class,
  SyntacticType.Coord2Nd to Coordinated.Conjunct::class,
  SyntacticType.Coord2NdNegative to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdAdvers to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdCompar to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdCorrelat to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdExplic to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdNeg to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class
  SyntacticType.Coord2NdSymmetric to Coordinated.Conjunct::class, // TODO: detail with a dedicated sub-class

  SyntacticType.Separator to Separator.Base::class,
  SyntacticType.SeparatorOpen to Separator.Open::class,
  SyntacticType.SeparatorClose to Separator.Close::class,
  SyntacticType.Quotes to Quotes.Base::class,
  SyntacticType.QuotesOpen to Quotes.Open::class,
  SyntacticType.QuotesClose to Quotes.Close::class,

  SyntacticType.Connector to Connector::class,

  SyntacticType.Contin to Contin.Base::class,
  SyntacticType.ContinDenom to Contin.Denom::class,
  SyntacticType.ContinLocut to Contin.Locut::class,
  SyntacticType.ContinNumber to Contin.Number::class,
  SyntacticType.ContinCoord to Contin.Coord::class,

  SyntacticType.End to End.Base::class,
  SyntacticType.EndAssertive to End.Assertive::class,
  SyntacticType.EndInterrogative to End.Interrogative::class,
  SyntacticType.EndImperative to End.Imperative::class,

  SyntacticType.Interj to Interjection::class,

  SyntacticType.Undefined to Undefined::class,
  SyntacticType.Unknown to Unknown::class,
  SyntacticType.Wrong to Wrong::class
)
