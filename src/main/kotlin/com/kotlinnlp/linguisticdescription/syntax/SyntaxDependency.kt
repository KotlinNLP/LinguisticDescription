/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax

/**
 * The base interface implemented by all the syntax dependencies.
 */
interface SyntaxDependency<T> {

  /**
   * The direction of the dependency.
   */
  enum class Direction {
    ROOT,
    LEFT,
    RIGHT,
    NULL
  }

  /**
   * The type of this dependency.
   */
  val type: T

  /**
   * The direction of the dependency, related to the governor.
   */
  val direction: Direction
}
