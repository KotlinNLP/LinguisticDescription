/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.utils.Regex

/**
 * A token with a form.
 */
interface FormToken : Token {

  /**
   * The form.
   */
  val form: String

  /**
   * Whether the form is a numeric values.
   */
  val isNumber: Boolean get() = Regex.numbers.matches(this.form)

  /**
   * Whether the form is a numeric values.
   */
  val isPunct: Boolean get() = Regex.punctuation.matches(this.form)

  /**
   * Whether the form is a comma.
   */
  val isComma: Boolean get() = this.form == ","

  /**
   * The normalized form (lower case and numbers replaced with a common form).
   */
  val normalizedForm: String get() = if (this.isNumber) "__num__" else this.form.toLowerCase()
}
