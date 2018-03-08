/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

import com.kotlinnlp.linguisticdescription.morphology.dictionary.MorphologyDictionary
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

  val dictionary: MorphologyDictionary = loadDictionary(filename = args[0])

  while (true) {

    val searchVal = readValue()

    if (searchVal.isEmpty())
      break
    else
      printMorphology(form = searchVal, dictionary = dictionary)
  }

  println("\nExiting...")
}

/**
 * @param filename the filename of the serialized dictionary
 *
 * @return a morphology dictionary
 */
private fun loadDictionary(filename: String): MorphologyDictionary {

  val timer = Timer()

  println("Loading serialized dictionary from '$filename'...")

  val m = MorphologyDictionary.load(FileInputStream(File(filename)))

  println("Elapsed time: %s".format(timer.formatElapsedTime()))

  println("\nNumber of elements in the dictionary: ${m.size} (+ ${m.alternativesCount} references).")

  return m
}

/**
 * Read a value from the standard input.
 *
 * @return the string read
 */
private fun readValue(): String {

  print("\nSearch a form (empty to exit): ")

  return readLine()!!
}

/**
 * Print the morphology of a form.
 *
 * @param form a form to search in the dictionary
 * @param dictionary the morphology dictionary
 */
private fun printMorphology(form: String, dictionary: MorphologyDictionary) {

  val multiWords: List<String> = dictionary.getMultiWords(form)

  println("\n" + dictionary[form])

  if (multiWords.isNotEmpty()) {
    println("'%s' is involved in the following multi-words expressions:\n\t%s"
      .format(form, multiWords.sorted().joinToString(separator = "\n\t")))
  }
}
