/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

/**
 * The position of an item.
 *
 * @property index the index of this item within a list of items
 * @property start the index of the starting char of this item within the original text
 * @property end the index of the ending char of this item within the original text
 */
data class Position(
  val index: Int,
  val start: Int,
  val end: Int
)
