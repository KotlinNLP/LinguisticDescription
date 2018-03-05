/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token.properties

/**
 * The 'diathesis' syntactic property.
 *
 * @property annotation the string used to annotate this property
 */
enum class Diathesis(val annotation: String) {
  Undefined("_"),
  Active("ACTIVE"),
  Passive("PASSIVE"),
  Middle("Middle")
}
