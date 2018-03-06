/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.lexicon.liwc

/**
 * A LIWC category type.
 *
 * @property annotation the string annotation of the category type
 */
enum class LIWCCategoryType(val annotation: String) {
  LinguisticProcess("Linguistic Process"),
  SocialProcess("Social process"),
  AffectiveProcess("Affective Process"),
  CognitiveProcess("Cognitive process"),
  PerceptualProcess("Perceptual process"),
  BiologicalProcess("Biological process"),
  Relativity("Relativity"),
  PersonalConcerns("Personal Concerns"),
  Spoken("Spoken")
}
