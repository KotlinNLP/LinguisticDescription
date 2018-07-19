/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.multiwords.datetime.intervals

import com.kotlinnlp.linguisticdescription.sentence.multiwords.datetime.SingleDateTime

/**
 * A date-time interval limited with a upper bound.
 */
interface UpperLimitedInterval : Interval {

  /**
   * The upper bound date-time.
   */
  val to: SingleDateTime
}