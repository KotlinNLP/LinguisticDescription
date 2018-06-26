/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties

import com.kotlinnlp.linguisticdescription.sentence.token.SyntacticToken
import com.kotlinnlp.linguisticdescription.sentence.token.Trace
import com.kotlinnlp.linguisticdescription.sentence.token.Word
import com.kotlinnlp.linguisticdescription.sentence.token.WordTrace
import java.util.*

/**
 * A datetime.
 *
 * @property tokens the list of tokens that compose this datetime
 * @property timeZone the timezone
 * @property isInterval whether this datetime represents an interval
 * @property suggestYear TODO: describe it
 * @property hasTime TODO: describe it
 * @property hasDate TODO: describe it
 * @property approxTime TODO: describe it
 * @property approxDate TODO: describe it
 */
data class DateTime(
  val tokens: List<SyntacticToken>,
  val timeZone: TimeZone,
  val isInterval: Boolean,
  val suggestYear: Boolean,
  val hasTime: Boolean,
  val hasDate: Boolean,
  val approxTime: Boolean,
  val approxDate: Boolean
) {

  /**
   * @return a string representation of this date-time
   */
  override fun toString(): String = "%s [%s]".format(
    this.tokens.joinToString(separator = " ") {
      when (it) {
        is Trace -> "-"
        is Word -> it.surface.form
        is WordTrace -> it.surface.form
        else -> throw RuntimeException("Invalid token.")
      }
    },
    this.timeZone
  )
}
