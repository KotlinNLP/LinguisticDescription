/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology

/**
 * The 'noun' morphology.
 */
sealed class Noun : Morphology, Thing, ContentWord {

  /**
   * The 'noun' morphology.
   */
  class Base : Noun() {

    override val type: MorphologyType = MorphologyType.Noun
  }

  /**
   * The 'common noun' morphology.
   */
  sealed class Common : Noun() {

    /**
     * The 'common noun' morphology.
     */
    class Base : Noun.Common() {

      override val type: MorphologyType = MorphologyType.NounCommon
    }

    /**
     * The 'common quantifying noun' morphology.
     */
    class Quantifying : Noun.Common() {

      override val type: MorphologyType = MorphologyType.NounCommonQuant
    }

    /**
     * The 'common gerundive noun' morphology.
     */
    class Gerundive : Noun.Common() {

      override val type: MorphologyType = MorphologyType.NounCommonGerund
    }
  }

  /**
   * The 'proper noun' morphology.
   */
  sealed class Proper : Noun() {

    /**
     * The 'proper noun' morphology.
     */
    class Base : Noun.Proper() {

      override val type: MorphologyType = MorphologyType.NounProper
    }

    /**
     * The 'person proper noun' morphology.
     */
    class Person : Noun.Proper() {

      override val type: MorphologyType = MorphologyType.NounProperPer
    }

    /**
     * The 'organization proper noun' morphology.
     */
    class Organization : Noun.Proper() {

      override val type: MorphologyType = MorphologyType.NounProperOrg
    }

    /**
     * The 'location proper noun' morphology.
     */
    class Location : Noun.Proper() {

      override val type: MorphologyType = MorphologyType.NounProperLoc
    }
  }
}
