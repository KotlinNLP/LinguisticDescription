/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.dictionary

/**
 * A data entry of the morphology dictionary.
 *
 * @property form the unique form of the entry
 * @property multipleForm the list of forms of the entry (null if it is composed by a single form)
 * @property morphologies the list of morphologies of the entry
 */
data class Entry(
  val form: String,
  val multipleForm: List<String>?,
  val morphologies: List<MorphologyEntry>
) {

  /**
   * @return a string representation of this entry
   */
  override fun toString(): String = """
    Form: '%s'
    Morphologies:
    %s%s
  """.trimIndent().format(this.form, "\t", this.morphologies.joinToString(separator = "\n\t"))
}
