/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.MorphologyDictionary
import utils.Timer
import java.io.File
import java.io.FileOutputStream

/**
 * Load a [MorphologyDictionary] from a file in JSONL format and serialize it to another file.
 *
 * Command line arguments:
 *   1. The path of the input file from which to read the dictionary in JSONL format.
 *   1. The path of the output file into which to write the serialized dictionary.
 */
fun main(args: Array<String>) {

  require(args.size == 2) { "Required 2 arguments: <input_file> <output_file>." }

  val inputFile: String = args[0]
  val outputFile: String = args[1]
  val timer = Timer()

  println("Loading morphology dictionary in JSONL format from '$inputFile'...")
  val m = MorphologyDictionary.load(inputFile)
  println("Elapsed time: %s".format(timer.formatElapsedTime()))

  println("\nNumber of elements in the dictionary: ${m.size}.")

  println("\nSaving serialized dictionary to '$outputFile'...")
  m.dump(FileOutputStream(File(outputFile)))
  println("Elapsed time: %s".format(timer.formatElapsedTime()))
}
