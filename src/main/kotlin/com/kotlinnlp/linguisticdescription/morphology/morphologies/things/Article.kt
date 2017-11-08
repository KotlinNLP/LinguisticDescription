/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.Label

/**
 * The 'article' morphology.
 */
sealed class Article : Thing {

  /**
   * The 'definite article' morphology.
   */
  class Definite : Article() {

    override val label = Label.ArtDef
  }

  /**
   * The 'indefinite article' morphology.
   */
  sealed class Indefinite : Article() {

    /**
     * The 'indefinite article' morphology.
     */
    class Base : Article.Indefinite() {

      override val label = Label.ArtIndef
    }

    /**
     * The 'indefinite partitive article' morphology.
     */
    class Partitive : Article.Indefinite() {

      override val label = Label.ArtIndefPart
    }
  }
}
