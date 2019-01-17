/* Copyright 2018-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence.properties.datetime

import java.lang.RuntimeException

/**
 * Raised when trying to manage a date that is not valid for various reasons (e.g. a DateOrdinal.Date containing
 * an year as value).
 *
 * @param message an error message
 */
open class InvalidDateTime(message: String = "") : RuntimeException(message)

/**
 * Raised when trying to manage a date that is not consistent with the Gregorian Calendar (e.g. "the 31st of February").
 *
 * @param message an error message
 */
class NotGregorianDateTime(message: String = "") : InvalidDateTime(message)
