/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty

/**
 * Raised when trying to create a Morphology without passing all required parameters.
 *
 * @param morphologyType the type of the morphology
 * @param propertyName the name of the missing parameter
 * @param lemma the lemma of the morphology
 */
class MissingMorphologyProperty(
  morphologyType: MorphologyType,
  propertyName: String,
  lemma: String
) : RuntimeException("'%s' of %s '%s'".trimIndent().format(propertyName, morphologyType, lemma))

/**
 * Raised when trying to get a [MorphologyType] by invalid annotation.
 *
 * @param typeAnnotation the annotation string of a [MorphologyType]
 */
class InvalidMorphologyType(typeAnnotation: String) : RuntimeException(typeAnnotation)

/**
 * Raised when trying to get a [MorphologyProperty] by invalid type.
 *
 * @param propertyType the type string of a [MorphologyProperty]
 */
class InvalidMorphologyPropertyType(propertyType: String) : RuntimeException(propertyType)

/**
 * Raised when trying to get a [MorphologyProperty] by invalid annotation.
 *
 * @param type the type of the property
 * @param annotation the annotation string of the missing property
 */
class InvalidMorphologyPropertyAnnotation(type: String, annotation: String) : RuntimeException(
  "'%s' invalid for %s".format(annotation, type)
)

/**
 * Raised when a given code is not within the supported ISO 639-1 language codes.
 *
 * @param code a language code
 */
class InvalidLanguageCode(code: String) : RuntimeException(code)
