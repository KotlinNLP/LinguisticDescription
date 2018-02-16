/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.MorphologyDictionary

/**
 * Load a [MorphologyDictionary] from a file in JSONL format.
 *
 * Command line arguments:
 *   1. The path of the input file from which to read the dictionary in JSONL format.
 */
fun main(args: Array<String>) {

  require(args.size == 1) { "Required 1 argument: <input_file>." }

  val inputFile: String = args[0]

  println("Loading morphology dictionary in JSONL format from '$inputFile'...")
  val m = MorphologyDictionary.load(inputFile)

  print("\nNumber of elements in the dictionary: ${m.size}.")
}
