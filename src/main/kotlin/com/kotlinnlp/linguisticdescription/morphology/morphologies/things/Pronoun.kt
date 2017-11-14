/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology

/**
 * The 'pronoun' morphology.
 */
sealed class Pronoun : Morphology, Thing {

  /**
   * The 'pronoun' morphology.
   */
  class Base : Pronoun() {

    override val type: MorphologyType = MorphologyType.Pron
  }

  /**
   * The 'exclamative pronoun' morphology.
   */
  class Exclamative : Pronoun() {

    override val type: MorphologyType = MorphologyType.PronExclam
  }

  /**
   * The 'ordinal pronoun' morphology.
   */
  class Ordinal : Pronoun() {

    override val type: MorphologyType = MorphologyType.PronOrdin
  }

  /**
   * The 'demonstrative pronoun' morphology.
   */
  class Demonstrative : Pronoun() {

    override val type: MorphologyType = MorphologyType.PronDemons
  }

  /**
   * The 'indefinite pronoun' morphology.
   */
  sealed class Indefinite : Pronoun() {

    /**
     * The 'indefinite pronoun' morphology.
     */
    class Base : Pronoun.Indefinite() {

      override val type: MorphologyType = MorphologyType.PronIndef
    }

    /**
     * The 'indefinite subordinating pronoun' morphology.
     */
    class Subordinating : Pronoun.Indefinite() {

      override val type: MorphologyType = MorphologyType.PronIndefSubord
    }

    /**
     * The 'indefinite distributive pronoun' morphology.
     */
    class Distributive : Pronoun.Indefinite() {

      override val type: MorphologyType = MorphologyType.PronIndefDistr
    }

    /**
     * The 'indefinite quantifying pronoun' morphology.
     */
    class Quantifying : Pronoun.Indefinite() {

      override val type: MorphologyType = MorphologyType.PronIndefQuant
    }
  }

  /**
   * The 'interrogative pronoun' morphology.
   */
  class Interrogative : Pronoun() {

    override val type: MorphologyType = MorphologyType.PronInterr
  }

  /**
   * The 'personal pronoun' morphology.
   */
  sealed class Personal : Pronoun() {

    /**
     * The 'personal pronoun' morphology.
     */
    class Base : Pronoun.Personal() {

      override val type: MorphologyType = MorphologyType.PronPers
    }

    /**
     * The 'reflexive personal pronoun' morphology.
     */
    class Reflexive : Pronoun.Personal() {

      override val type: MorphologyType = MorphologyType.PronPersRefl
    }

    /**
     * The 'variant personal pronoun' morphology.
     */
    class Variant : Pronoun.Personal() {

      override val type: MorphologyType = MorphologyType.PronPersVariant
    }

    /**
     * The 'enclitic personal pronoun' morphology.
     */
    class Enclitic : Pronoun.Personal() {

      override val type: MorphologyType = MorphologyType.PronPersEnclit
    }

    /**
     * The 'proclitic personal pronoun' morphology.
     */
    sealed class Proclitic : Pronoun() {

      /**
       * The 'proclitic personal pronoun' morphology.
       */
      class Base : Pronoun.Personal.Proclitic() {

        override val type: MorphologyType = MorphologyType.PronPersProclit
      }

      /**
       * The 'proclitic reflexive personal pronoun' morphology.
       */
      class Reflexive : Pronoun.Personal.Proclitic() {

        override val type: MorphologyType = MorphologyType.PronPersProclitRefl
      }

      /**
       * The 'proclitic variant personal pronoun' morphology.
       */
      class Variant : Pronoun.Personal.Proclitic() {

        override val type: MorphologyType = MorphologyType.PronPersProclitVariant
      }
    }
  }

  /**
   * The 'possessive pronoun' morphology.
   */
  class Possessive : Pronoun() {

    override val type: MorphologyType = MorphologyType.PronPoss
  }

  /**
   * The 'relative pronoun' morphology.
   */
  sealed class Relative : Pronoun() {

    /**
     * The 'relative pronoun' morphology.
     */
    class Base : Pronoun.Relative() {

      override val type: MorphologyType = MorphologyType.PronRelat
    }

    /**
     * The 'relative double pronoun' morphology.
     */
    class Double : Pronoun.Relative() {

      override val type: MorphologyType = MorphologyType.PronRelatDouble
    }
  }
}
