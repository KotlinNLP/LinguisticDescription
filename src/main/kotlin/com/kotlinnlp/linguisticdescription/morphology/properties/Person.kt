/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.properties

/**
 * The 'person' morphology property.
 *
 * @property annotation the string used to annotate this property
 */
enum class Person(override val annotation: String) : MorphologyProperty {
  First("1"),
  Second("2"),
  Third("3")
}