/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.sentence

import com.kotlinnlp.linguisticdescription.sentence.token.Token
import com.kotlinnlp.linguisticdescription.sentence.token.properties.Positionable

/**
 * A real sentence, with a position in the original text.
 */
interface RealSentence<TokenType: Token> : Sentence<TokenType>, Positionable
