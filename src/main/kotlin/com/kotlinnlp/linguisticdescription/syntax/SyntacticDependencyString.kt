/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.syntax


/**
 * The syntactic dependency with a string type.
 *
 * @property type the type of this dependency
 * @property direction the direction of the dependency, related to the governor
 */
data class SyntacticDependencyString(
  override val type: String,
  override val direction: SyntacticDependency.Direction
) : SyntacticDependency<String>
