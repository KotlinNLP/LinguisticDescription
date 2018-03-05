/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

/**
 * The surface of a token.
 *
 * @property form the form
 * @property lowerCaseForm the form in lower case
 * @property followedBySpace whether this token is followed by a space in the original text
 */
data class Surface(
  val form: String,
  val lowerCaseForm: String,
  val followedBySpace: Boolean
)
