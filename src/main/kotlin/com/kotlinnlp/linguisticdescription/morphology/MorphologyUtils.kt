/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

import com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Number
import kotlin.reflect.KClass

/**
 * The map of POS to the related morphology k-classes.
 */
internal val morphologyClasses = mapOf<POS, KClass<*>>(

  POS.Adj to Adjective.Base::class,
  POS.AdjCompar to Adjective.Comparative::class,
  POS.AdjDeict to Adjective.Deictic::class,
  POS.AdjDemons to Adjective.Demonstrative.Base::class,
  POS.AdjDemonsAntec to Adjective.Demonstrative.Antecedent::class,
  POS.AdjDemonsSucc to Adjective.Demonstrative.Successive::class,
  POS.AdjExclam to Adjective.Exclamative::class,
  POS.AdjIndef to Adjective.Indefinite.Base::class,
  POS.AdjIndefDistr to Adjective.Indefinite.Distributive::class,
  POS.AdjIndefQuant to Adjective.Indefinite.Quantifying::class,
  POS.AdjIndefSubord to Adjective.Indefinite.Subordinating::class,
  POS.AdjInterr to Adjective.Interrogative::class,
  POS.AdjPoss to Adjective.Possessive::class,
  POS.AdjQualif to Adjective.Qualifying.Base::class,
  POS.AdjQualifPost to Adjective.Qualifying.Postpositive::class,
  POS.AdjOrdin to Adjective.Ordinal::class,
  POS.AdjRelat to Adjective.Relative::class,

  POS.AdvAdvers to Adverb.Adversative::class,
  POS.AdvCompar to Adverb.Comparative::class,
  POS.AdvConcess to Adverb.Concessive::class,
  POS.AdvDeict to Adverb.Deictic::class,
  POS.AdvIndef to Adverb.Indefinite::class,
  POS.AdvIndefSubord to Adverb.IndefiniteSubordinating::class,
  POS.AdvInterr to Adverb.Interrogative::class,
  POS.AdvLimit to Adverb.Limiting::class,
  POS.AdvLoc to Adverb.Locative::class,
  POS.AdvModal to Adverb.Modal::class,
  POS.AdvNeg to Adverb.Negative::class,
  POS.AdvPhras to Adverb.Phrase::class,
  POS.AdvQuant to Adverb.Quantitative::class,
  POS.AdvStreng to Adverb.Strength.Base::class,
  POS.AdvStrengNeg to Adverb.Strength.Negative::class,
  POS.AdvTime to Adverb.Time::class,

  POS.Art to Article.Base::class,
  POS.ArtDef to Article.Definite::class,
  POS.ArtIndef to Article.Indefinite.Base::class,
  POS.ArtIndefPart to Article.Indefinite.Partitive::class,

  POS.Conj to Conjunction.Base::class,
  POS.ConjCompar to Conjunction.Comparative.Base::class,
  POS.ConjComparAntec to Conjunction.Comparative.Antecedent::class,
  POS.ConjComparSucc to Conjunction.Comparative.Successive::class,
  POS.ConjConcess to Conjunction.Concessive::class,
  POS.ConjCoord to Conjunction.Coordinating.Base::class,
  POS.ConjCoordAdvers to Conjunction.Coordinating.Adversative::class,
  POS.ConjCoordDisj to Conjunction.Coordinating.Disjunctive::class,
  POS.ConjCoordExplic to Conjunction.Coordinating.Explicit::class,
  POS.ConjCoordNeg to Conjunction.Coordinating.Negative::class,
  POS.ConjCorrel to Conjunction.Correlating.Base::class,
  POS.ConjCorrelAntec to Conjunction.Correlating.Antecedent::class,
  POS.ConjCorrelSucc to Conjunction.Correlating.Successive::class,
  POS.ConjSubord to Conjunction.Subordinating.Base::class,
  POS.ConjSubordAdvers to Conjunction.Subordinating.Adversative::class,
  POS.ConjSubordInterr to Conjunction.Subordinating.Interrogative::class,

  POS.Noun to Noun.Base::class,
  POS.NounCommon to Noun.Common.Base::class,
  POS.NounCommonGerund to Noun.Common.Gerundive::class,
  POS.NounCommonQuant to Noun.Common.Quantifying::class,
  POS.NounProper to Noun.Proper.Base::class,
  POS.NounProperLoc to Noun.Proper.Location::class,
  POS.NounProperOrg to Noun.Proper.Organization::class,
  POS.NounProperPer to Noun.Proper.Person::class,

  POS.Pron to Pronoun.Base::class,
  POS.PronDemons to Pronoun.Demonstrative::class,
  POS.PronExclam to Pronoun.Exclamative::class,
  POS.PronIndef to Pronoun.Indefinite.Base::class,
  POS.PronIndefDistr to Pronoun.Indefinite.Distributive::class,
  POS.PronIndefQuant to Pronoun.Indefinite.Quantifying::class,
  POS.PronIndefSubord to Pronoun.Indefinite.Subordinating::class,
  POS.PronInterr to Pronoun.Interrogative::class,
  POS.PronOrdin to Pronoun.Ordinal::class,
  POS.PronPers to Pronoun.Personal.Base::class,
  POS.PronPersEnclit to Pronoun.Personal.Enclitic::class,
  POS.PronPersProclit to Pronoun.Personal.Proclitic.Base::class,
  POS.PronPersProclitRefl to Pronoun.Personal.Proclitic.Reflexive::class,
  POS.PronPersProclitVariant to Pronoun.Personal.Proclitic.Variant::class,
  POS.PronPersRefl to Pronoun.Personal.Reflexive::class,
  POS.PronPersVariant to Pronoun.Personal.Variant::class,
  POS.PronPoss to Pronoun.Possessive::class,
  POS.PronRelat to Pronoun.Relative.Base::class,
  POS.PronRelatDouble to Pronoun.Relative.Double::class,

  POS.Prep to Preposition.Base::class,
  POS.PrepArt to Preposition.Articulated::class,
  POS.PrepPoss to Preposition.Possessive::class,
  POS.PrepCompar to Preposition.Comparative::class,

  POS.Postpos to Postposition.Base::class,
  POS.PostposPoss to Postposition.Possessive::class,

  POS.Interj to Interjection::class,
  POS.PhrasAff to Phrase.Affirmative::class,
  POS.PhrasExclam to Phrase.Exclamative::class,
  POS.PhrasInterr to Phrase.Interrogative::class,
  POS.PhrasNeg to Phrase.Negative::class,
  POS.Punct to Punctuation::class,

  POS.Verb to Verb.Base::class,
  POS.VerbAux to Verb.Auxiliary::class,
  POS.VerbModal to Verb.Modal::class,

  POS.Date to Date::class,
  POS.Hour to Hour::class,
  POS.Num to Number::class,
  POS.Predet to Predeterminer::class,

  POS.Unknown to Unknown::class
)
