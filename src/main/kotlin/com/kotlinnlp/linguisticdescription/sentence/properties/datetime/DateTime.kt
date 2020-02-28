/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import com.kotlinnlp.linguisticdescription.sentence.properties.TokensRange
import com.kotlinnlp.utils.JSONSerializable

/**
 * A date-time expression.
 */
interface DateTime : TokensRange, JSONSerializable {

  /**
   * Get the string representing this date-time in the standard format.
   *
   * @return the standard string representing this date-time
   */
  fun toStandardFormat(): String
}
