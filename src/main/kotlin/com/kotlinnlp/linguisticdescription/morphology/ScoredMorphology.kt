/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

/**
 * Extension of the [Morphology] with a [score] property.
 *
 * @property type the type of this morphology (Single or Multiple)
 * @property list a list of single morphologies
 * @property score the score assigned to this morphology
 */
class ScoredMorphology(
  type: Type,
  list: List<SingleMorphology>,
  val score: Double
): Morphology(type, list)
