/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.compressor

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType

/**
 * A temporary morphology.
 *
 * @property lemma the lemma
 * @property type the morphology type
 * @property properties the morphology properties object
 */
data class TmpMorphology(val lemma: String, val type: MorphologyType, val properties: Properties)
