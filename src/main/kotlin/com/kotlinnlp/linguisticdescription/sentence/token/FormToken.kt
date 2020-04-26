/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

/**
 * A token with a form.
 */
interface FormToken : Token {

  companion object {

    /**
     * Regex pattern for numeric values.
     */
    val numberRegex = Regex("^\\d+(?:[,.]\\d+)*(?:\\[.,]\\d+)?\$")

    /**
     * A regex that matches a punctuation token.
     */
    private val punctRegex = Regex("^[….,;:#!?|/\\\\$%&=~*\\-–_\"“”″‘'`^()<>«»\\[\\]{}]+$")
  }

  /**
   * The form.
   */
  val form: String

  /**
   * Whether the form is a numeric values.
   */
  val isNumber: Boolean get() = numberRegex.matches(this.form)

  /**
   * Whether the form is a numeric values.
   */
  val isPunct: Boolean get() = punctRegex.matches(this.form)

  /**
   * Whether the form is a comma.
   */
  val isComma: Boolean get() = this.form == ","

  /**
   * The normalized form (lower case and numbers replaced with a common form).
   */
  val normalizedForm: String get() = if (this.isNumber) "__num__" else this.form.toLowerCase()
}
