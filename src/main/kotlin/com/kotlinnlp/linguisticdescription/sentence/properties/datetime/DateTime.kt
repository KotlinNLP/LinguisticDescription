/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.sentence.properties.TokensRange

/**
 * A date-time expression.
 */
interface DateTime : TokensRange {

  /**
   * Get the string representing this date-time in the standard format.
   *
   * @return the standard string representing this date-time
   */
  fun toStandardFormat(): String

  /**
   * @return the JSON object that represents this multi-words expression
   */
  fun toJSON(): JsonObject {
    TODO("make as abstract and implement for each implementation")
  }
}
