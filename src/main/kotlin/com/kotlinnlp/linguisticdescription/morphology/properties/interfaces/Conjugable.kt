/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties.interfaces

import com.kotlinnlp.linguisticdescription.morphology.properties.Mood
import com.kotlinnlp.linguisticdescription.morphology.properties.Tense

/**
 * A morphology with [Tense] and [Mood] properties.
 */
interface Conjugable {

  /**
   * The tense of a verb.
   */
  val tense: Tense

  /**
   * The mood of a verb.
   */
  val mood: Mood
}
