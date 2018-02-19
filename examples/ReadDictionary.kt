/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.MorphologyDictionary
import utils.Timer
import java.io.File
import java.io.FileInputStream

/**
 * Load a serialized [MorphologyDictionary] from a file and read values from it.
 *
 * Command line arguments:
 *   1. The path of the input file from which to read the serialized dictionary.
 */
fun main(args: Array<String>) {

  require(args.size == 1) { "Required 1 argument: <input_file>." }

  val inputFile: String = args[0]
  val timer = Timer()

  println("Loading serialized dictionary from '$inputFile'...")
  val m = MorphologyDictionary.load(FileInputStream(File(inputFile)))
  println("Elapsed time: %s".format(timer.formatElapsedTime()))

  println("\nNumber of elements in the dictionary: ${m.size}.")

  while (true) {

    print("\nSearch a form (empty to exit): ")
    val searchVal = readLine()!!

    if (searchVal.isEmpty())
      break
    else
      println("\n" + m[searchVal])
  }

  println("\nExiting...")
}
