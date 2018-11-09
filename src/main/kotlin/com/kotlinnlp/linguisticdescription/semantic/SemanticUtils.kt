/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.semantic

import javax.sound.midi.Instrument
import kotlin.reflect.KClass

/**
 * The map of semantic k-classes associated by semantic type.
 */
internal val semanticClasses = mapOf<SemanticType, KClass<*>>(

  SemanticType.SpaceTime to SpaceTime.Base::class,
  SemanticType.SpaceTimeInterval to SpaceTime.Interval::class,
  SemanticType.SpaceTimeStart to SpaceTime.Start::class,
  SemanticType.SpaceTimeEnd to SpaceTime.End::class,

  SemanticType.Temporal to Temporal.Base::class,
  SemanticType.TemporalStart to Temporal.Start::class,
  SemanticType.TemporalEnd to Temporal.End::class,
  SemanticType.TemporalPrev to Temporal.Prev::class,
  SemanticType.TemporalPost to Temporal.Post::class,
  SemanticType.TemporalProximity to Temporal.Proximity::class,

  SemanticType.Location to Location.Base::class,
  SemanticType.LocationInside to Location.Inside::class,
  SemanticType.LocationDestination to Location.Destination::class,
  SemanticType.LocationDistant to Location.Distant::class,
  SemanticType.LocationUp to Location.Up::class,
  SemanticType.LocationDown to Location.Down::class,
  SemanticType.LocationAround to Location.Around::class,
  SemanticType.LocationSource to Location.Source::class,
  SemanticType.LocationProximity to Location.Proximity::class,
  SemanticType.LocationContact to Location.Contact::class,
  SemanticType.LocationSide to Location.Side.Base::class,
  SemanticType.LocationSideRight to Location.Side.Right::class,
  SemanticType.LocationSideLeft to Location.Side.Left::class,

  SemanticType.Comparative to Comparative.Base::class,
  SemanticType.ComparativeLimitative to Comparative.Limitative::class,
  SemanticType.ComparativeAccrescitive to Comparative.Accrescitive::class,
  SemanticType.ComparativeEqual to Comparative.Equal.Base::class,
  SemanticType.ComparativeEqualNegation to Comparative.Equal.Negation::class,

  SemanticType.Manner to Manner.Base::class,
  SemanticType.MannerEqual to Manner.Equal.Base::class,
  SemanticType.MannerUnequal to Manner.Equal.Negation::class,

  SemanticType.Modal to Modal::class,
  SemanticType.Conditional to Conditional::class,
  SemanticType.Concessive to Concessive::class,
  SemanticType.Causal to Causal::class,
  SemanticType.Reason to Reason::class,
  SemanticType.Reasoncause to ReasonCause::class,
  SemanticType.Advers to Adversative::class,
  SemanticType.Limitative to Limitative::class,
  SemanticType.Goal to Goal::class,
  SemanticType.Exceptive to Exceptive::class,
  SemanticType.Explicative to Explicative::class,
  SemanticType.Theme to Theme::class,
  SemanticType.Progress to Progress::class,
  SemanticType.Union to Union::class,
  SemanticType.Disunion to Disunion::class,
  SemanticType.Concerns to Concerns::class,
  SemanticType.Partitive to Partitive::class,

  SemanticType.Specification to Specification.Base::class,
  SemanticType.SpecificationDenominative to Specification.Denominative::class,

  SemanticType.Companionship to Companionship.Base::class,
  SemanticType.CompanionshipFigurative to Companionship.Figurative::class,

  SemanticType.Beneficiary to Beneficiary.Base::class,
  SemanticType.BeneficiaryDisvantage to Beneficiary.Disadvantaged::class,

  SemanticType.Means to Means::class,
  SemanticType.Instrument to Instrument::class,
  SemanticType.Material to Material::class,
  SemanticType.Fault to Fault::class,
  SemanticType.Penalty to Penalty::class,
  SemanticType.Replacement to Replacement::class
)
