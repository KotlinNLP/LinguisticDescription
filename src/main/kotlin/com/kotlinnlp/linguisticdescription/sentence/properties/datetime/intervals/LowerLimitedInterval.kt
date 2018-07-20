/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime.intervals

import com.kotlinnlp.linguisticdescription.sentence.properties.datetime.SingleDateTime

/**
 * A date-time interval limited with a lower bound.
 */
interface LowerLimitedInterval : Interval {

  /**
   * The lower bound date-time.
   */
  val from: SingleDateTime
}