/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.lexicon

/**
 * A sentiment category.
 *
 * @property type the category type
 * @property annotation the string annotation of the category
 */
enum class SentimentCategory(val type: String, val annotation: String) {
  Pronoun("Linguistic process", "pronoun"),
  I("Linguistic process - Pronoun", "i"),
  We("Linguistic process - Pronoun", "we"),
  You("Linguistic process - Pronoun", "you"),
  Article("Linguistic process", "article"),
  Present("Linguistic process", "present"),
  Future("Linguistic process", "future"),
  Prepositions("Linguistic process", "preps"),
  Negations("Linguistic process", "negate"),
  Numbers("Linguistic process", "numbers"),
  SwearWords("Linguistic process", "swear"),
  SocialProcess("Social processes", "social"),
  Family("Social processes", "family"),
  Friendly("Social processes", "friend"),
  Human("Social processes", "humans"),
  Affect("Affective Process", "affect"),
  PositiveEmotion("Affective process", "posemo"),
  NegativeEmotion("Affective processes", "negemo"),
  Anxiety("Affective processes - Negative Emotion", "anx"),
  Anger("Affective processes - Negative Emotion", "anger"),
  Sadness("Affective processes - Negative Emotion", "sad"),
  CognitiveProcess("Cognitive process", "cogmech"),
  Insight("Cognitive process", "insight"),
  Causation("Cognitive process", "cause"),
  Discrepancy("Cognitive process", "discrep"),
  Tentative("Cognitive process", "tentat"),
  Certainty("Cognitive process", "certain"),
  Inhibition("Cognitive process", "inhib"),
  Inclusive("Cognitive process", "incl"),
  Exclusive("Cognitive process", "excl"),
  See("Perceptual processes", "see"),
  Hear("Perceptual processes", "hear"),
  Feel("Perceptual processes", "feel"),
  BiologicalProcess("Biological process", "bio"),
  body("Biological process", "body"),
  Sexual("Biological process", "sexual"),
  Ingestion("Biological process", "ingest"),
  Relativity("", "relativ"),
  Motion("Relativity", "motion"),
  Space("Relativity", "space"),
  Time("Relativity", "time"),
  Work("Personal Concerns", "work"),
  Achievement("Personal Concerns", "achieve"),
  Leisure("Personal Concerns", "leisure"),
  Home("Personal Concerns", "home"),
  Religion("Personal Concerns", "relig"),
  Death("Personal Concerns", "death"),
  Assent("Spoken - Assent", "assent"),
  Nonfluencies("Spoken", "nonfl"),
  Fillers("Spoken", "filler")
}
