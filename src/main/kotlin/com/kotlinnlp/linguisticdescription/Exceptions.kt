/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.properties.GrammaticalProperty

/**
 * Raised when trying to create a Morphology without passing all required parameters.
 *
 * @param pos the POS
 * @param propertyName the name of the missing parameter
 * @param lemma the lemma of the morphology
 */
class MissingMorphologyProperty(
  pos: POS,
  propertyName: String,
  lemma: String
) : RuntimeException("'%s' of %s '%s'".trimIndent().format(propertyName, pos, lemma))

/**
 * Raised when trying to get a [POS] by invalid annotation.
 *
 * @param annotation the annotation string of a [POS]
 */
class InvalidPOS(annotation: String) : RuntimeException(annotation)

/**
 * Raised when trying to get a [GrammaticalProperty] by invalid name.
 *
 * @param propertyType the type string of a [GrammaticalProperty]
 */
class InvalidGrammaticalPropertyName(propertyType: String) : RuntimeException(propertyType)

/**
 * Raised when trying to get a [GrammaticalProperty] by invalid annotation.
 *
 * @param type the type of the property
 * @param annotation the annotation string of the missing property
 */
class InvalidGrammaticalPropertyAnnotation(type: String, annotation: String) : RuntimeException(
  "'%s' invalid for %s".format(annotation, type)
)

/**
 * Raised when a given code is not within the supported ISO 639-1 language codes.
 *
 * @param code a language code
 */
class InvalidLanguageCode(code: String) : RuntimeException(code)
