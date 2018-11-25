/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology

/**
 * Simple Morphology list wrapper to keep the code clean (e.g., avoiding inconvenient List<List<Morphology>>).
 *
 * @param list the list of all the possible alternative morphologies (can be empty)
 */
class Morphologies(private val list: List<Morphology> = emptyList()) : List<Morphology> by list {

  /**
   * Build a [Morphologies] given one [Morphology].
   *
   * @param morphology a morphology
   */
  constructor(morphology: Morphology): this(listOf(morphology))
}
