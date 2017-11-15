/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.utils

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType

/**
 * Raised when trying to create a Morphology without passing all required parameters.
 *
 * @param propertyName the name of the missing parameter
 */
class MissingMorphologyProperty(propertyName: String) : RuntimeException(propertyName)

/**
 * Raised when trying to get a [MorphologyType] by invalid annotation.
 *
 * @param typeAnnotation the annotation string of a [MorphologyType]
 */
class InvalidMorphologyType(typeAnnotation: String) : RuntimeException(typeAnnotation)
