/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.semantic

/**
 *
 */
sealed class Location {

  /**
   *
   */
  abstract val semanticType: SemanticType

  /**
   *
   */
  class Base : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.Location
  }

  /**
   *
   */
  class Inside : Location()  {

    /**
     *
     */
    override val semanticType = SemanticType.LocationInside
  }

  /**
   *
   */
  class Destination : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationDestination
  }


  /**
   *
   */
  class Distant : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationDistant
  }

  /**
   *
   */
  class Up : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationUp
  }

  /**
   *
   */
  class Down : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationDown
  }

  /**
   *
   */
  class Around : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationAround
  }

  /**
   *
   */
  class Source : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationSource
  }

  /**
   *
   */
  class Proximity : Location()
  {

    /**
     *
     */
    override val semanticType = SemanticType.LocationProximity
  }

  /**
   *
   */
  class Contact : Location() {

    /**
     *
     */
    override val semanticType = SemanticType.LocationContact
  }

  /**
   *
   */
  sealed class Side : Location() {

    /**
     *
     */
    class Base: Side() {

      /**
       *
       */
      override val semanticType = SemanticType.LocationSide
    }

    /**
     *
     */
    class Left : Side() {

      /**
       *
       */
      override val semanticType = SemanticType.LocationSideLeft
    }

    /**
     *
     */
    class Right : Side() {

      /**
       *
       */
      override val semanticType = SemanticType.LocationSideRight
    }
  }
}