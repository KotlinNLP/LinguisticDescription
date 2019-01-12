/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import java.time.LocalDateTime

/**
 * A date-time composed by a single element (not an interval).
 */
interface SingleDateTime : DateTime {

  /**
   * @return the LocalDateTime object representing this date-time expression
   */
  fun toLocalDateTime(): LocalDateTime
}
