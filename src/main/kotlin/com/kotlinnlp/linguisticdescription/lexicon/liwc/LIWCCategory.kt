/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.lexicon.liwc

/**
 * A LIWC category.
 *
 * Reference: http://liwc.wpengine.com
 *
 * @property type the category type
 * @property annotation the string annotation of the category
 */
enum class LIWCCategory(val type: LIWCCategoryType, val annotation: String) {
  Pronoun(type = LIWCCategoryType.LinguisticProcess, annotation = "pronoun"),
  I(type = LIWCCategoryType.LinguisticProcess, annotation = "i"),
  We(type = LIWCCategoryType.LinguisticProcess, annotation = "we"),
  You(type = LIWCCategoryType.LinguisticProcess, annotation = "you"),
  Article(type = LIWCCategoryType.LinguisticProcess, annotation = "article"),
  Present(type = LIWCCategoryType.LinguisticProcess, annotation = "present"),
  Future(type = LIWCCategoryType.LinguisticProcess, annotation = "future"),
  Prepositions(type = LIWCCategoryType.LinguisticProcess, annotation = "preps"),
  Negations(type = LIWCCategoryType.LinguisticProcess, annotation = "negate"),
  Numbers(type = LIWCCategoryType.LinguisticProcess, annotation = "numbers"),
  SwearWords(type = LIWCCategoryType.LinguisticProcess, annotation = "swear"),
  SocialProcess(type = LIWCCategoryType.SocialProcess, annotation = "social"),
  Family(type = LIWCCategoryType.SocialProcess, annotation = "family"),
  Friendly(type = LIWCCategoryType.SocialProcess, annotation = "friend"),
  Human(type = LIWCCategoryType.SocialProcess, annotation = "humans"),
  Affect(type = LIWCCategoryType.AffectiveProcess, annotation = "affect"),
  PositiveEmotion(type = LIWCCategoryType.AffectiveProcess, annotation = "posemo"),
  NegativeEmotion(type = LIWCCategoryType.AffectiveProcess, annotation = "negemo"),
  Anxiety(type = LIWCCategoryType.AffectiveProcess, annotation = "anx"),
  Anger(type = LIWCCategoryType.AffectiveProcess, annotation = "anger"),
  Sadness(type = LIWCCategoryType.AffectiveProcess, annotation = "sad"),
  CognitiveProcess(type = LIWCCategoryType.CognitiveProcess, annotation = "cogmech"),
  Insight(type = LIWCCategoryType.CognitiveProcess, annotation = "insight"),
  Causation(type = LIWCCategoryType.CognitiveProcess, annotation = "cause"),
  Discrepancy(type = LIWCCategoryType.CognitiveProcess, annotation = "discrep"),
  Tentative(type = LIWCCategoryType.CognitiveProcess, annotation = "tentat"),
  Certainty(type = LIWCCategoryType.CognitiveProcess, annotation = "certain"),
  Inhibition(type = LIWCCategoryType.CognitiveProcess, annotation = "inhib"),
  Inclusive(type = LIWCCategoryType.CognitiveProcess, annotation = "incl"),
  Exclusive(type = LIWCCategoryType.CognitiveProcess, annotation = "excl"),
  See(type = LIWCCategoryType.PerceptualProcess, annotation = "see"),
  Hear(type = LIWCCategoryType.PerceptualProcess, annotation = "hear"),
  Feel(type = LIWCCategoryType.PerceptualProcess, annotation = "feel"),
  BiologicalProcess(type = LIWCCategoryType.BiologicalProcess, annotation = "bio"),
  Body(type = LIWCCategoryType.BiologicalProcess, annotation = "body"),
  Sexual(type = LIWCCategoryType.BiologicalProcess, annotation = "sexual"),
  Ingestion(type = LIWCCategoryType.BiologicalProcess, annotation = "ingest"),
  Relativity(type = LIWCCategoryType.Relativity, annotation = "relativ"),
  Motion(type = LIWCCategoryType.Relativity, annotation = "motion"),
  Space(type = LIWCCategoryType.Relativity, annotation = "space"),
  Time(type = LIWCCategoryType.Relativity, annotation = "time"),
  Work(type = LIWCCategoryType.PersonalConcerns, annotation = "work"),
  Achievement(type = LIWCCategoryType.PersonalConcerns, annotation = "achieve"),
  Leisure(type = LIWCCategoryType.PersonalConcerns, annotation = "leisure"),
  Home(type = LIWCCategoryType.PersonalConcerns, annotation = "home"),
  Religion(type = LIWCCategoryType.PersonalConcerns, annotation = "relig"),
  Death(type = LIWCCategoryType.PersonalConcerns, annotation = "death"),
  Assent(type = LIWCCategoryType.Spoken, annotation = "assent"),
  NonFluencies(type = LIWCCategoryType.Spoken, annotation = "nonfl"),
  Fillers(type = LIWCCategoryType.Spoken,annotation =  "filler")
}
