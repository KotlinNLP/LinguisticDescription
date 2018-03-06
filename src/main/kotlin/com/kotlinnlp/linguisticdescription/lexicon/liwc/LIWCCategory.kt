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
  Pronoun(LIWCCategoryType.LinguisticProcess, "pronoun"),
  I(LIWCCategoryType.LinguisticProcess, "i"),
  We(LIWCCategoryType.LinguisticProcess, "we"),
  You(LIWCCategoryType.LinguisticProcess, "you"),
  Article(LIWCCategoryType.LinguisticProcess, "article"),
  Present(LIWCCategoryType.LinguisticProcess, "present"),
  Future(LIWCCategoryType.LinguisticProcess, "future"),
  Prepositions(LIWCCategoryType.LinguisticProcess, "preps"),
  Negations(LIWCCategoryType.LinguisticProcess, "negate"),
  Numbers(LIWCCategoryType.LinguisticProcess, "numbers"),
  SwearWords(LIWCCategoryType.LinguisticProcess, "swear"),
  SocialProcess(LIWCCategoryType.SocialProcess, "social"),
  Family(LIWCCategoryType.SocialProcess, "family"),
  Friendly(LIWCCategoryType.SocialProcess, "friend"),
  Human(LIWCCategoryType.SocialProcess, "humans"),
  Affect(LIWCCategoryType.AffectiveProcess, "affect"),
  PositiveEmotion(LIWCCategoryType.AffectiveProcess, "posemo"),
  NegativeEmotion(LIWCCategoryType.AffectiveProcess, "negemo"),
  Anxiety(LIWCCategoryType.AffectiveProcess, "anx"),
  Anger(LIWCCategoryType.AffectiveProcess, "anger"),
  Sadness(LIWCCategoryType.AffectiveProcess, "sad"),
  CognitiveProcess(LIWCCategoryType.CognitiveProcess, "cogmech"),
  Insight(LIWCCategoryType.CognitiveProcess, "insight"),
  Causation(LIWCCategoryType.CognitiveProcess, "cause"),
  Discrepancy(LIWCCategoryType.CognitiveProcess, "discrep"),
  Tentative(LIWCCategoryType.CognitiveProcess, "tentat"),
  Certainty(LIWCCategoryType.CognitiveProcess, "certain"),
  Inhibition(LIWCCategoryType.CognitiveProcess, "inhib"),
  Inclusive(LIWCCategoryType.CognitiveProcess, "incl"),
  Exclusive(LIWCCategoryType.CognitiveProcess, "excl"),
  See(LIWCCategoryType.PerceptualProcess, "see"),
  Hear(LIWCCategoryType.PerceptualProcess, "hear"),
  Feel(LIWCCategoryType.PerceptualProcess, "feel"),
  BiologicalProcess(LIWCCategoryType.BiologicalProcess, "bio"),
  body(LIWCCategoryType.BiologicalProcess, "body"),
  Sexual(LIWCCategoryType.BiologicalProcess, "sexual"),
  Ingestion(LIWCCategoryType.BiologicalProcess, "ingest"),
  Relativity(LIWCCategoryType.Relativity, "relativ"),
  Motion(LIWCCategoryType.Relativity, "motion"),
  Space(LIWCCategoryType.Relativity, "space"),
  Time(LIWCCategoryType.Relativity, "time"),
  Work(LIWCCategoryType.PersonalConcerns, "work"),
  Achievement(LIWCCategoryType.PersonalConcerns, "achieve"),
  Leisure(LIWCCategoryType.PersonalConcerns, "leisure"),
  Home(LIWCCategoryType.PersonalConcerns, "home"),
  Religion(LIWCCategoryType.PersonalConcerns, "relig"),
  Death(LIWCCategoryType.PersonalConcerns, "death"),
  Assent(LIWCCategoryType.Spoken, "assent"),
  Nonfluencies(LIWCCategoryType.Spoken, "nonfl"),
  Fillers(LIWCCategoryType.Spoken, "filler")
}
