/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

/**
 * A map of languages associated by ISO code.
 */
private val languagesByIso: Map<String, Language> = Language.values().associateBy { it.isoCode }

/**
 * @param isoCode the ISO 639-1 code of a language
 *
 * @throws RuntimeException when the given ISO code is not valid
 *
 * @return the language with the given code
 */
fun getLanguageByIso(isoCode: String): Language = languagesByIso[isoCode] ?: throw InvalidLanguageCode(isoCode)
