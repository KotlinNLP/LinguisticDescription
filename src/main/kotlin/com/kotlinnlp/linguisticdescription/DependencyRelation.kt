/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription

import java.io.Serializable

/**
 * A dependency relation composed by a POS tag and a deprel.
 *
 * @property posTag the POS tag (can be null)
 * @property deprel the deprel
 */
data class DependencyRelation(val posTag: POSTag? = null, val deprel: Deprel) : Serializable {

  companion object {

    /**
     * Private val used to serialize the class (needed by Serializable).
     */
    @Suppress("unused")
    private const val serialVersionUID: Long = 1L
  }
}
