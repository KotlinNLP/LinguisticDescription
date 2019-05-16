/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.beust.klaxon.JsonObject
import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number as NumberProp
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.PersonDeclinable

/**
 * The 'number' morphology.
 *
 * Note:
 *  Number morphologies cannot be put in the dictionary because they have an adding 'numericForm' property.
 *  They should be created using a Morphological Analyzer.
 *
 * @property lemma the lemma (the standard representation of the number in digits)
 * @property gender the 'gender' grammatical property
 * @property number the 'number' grammatical property
 * @property numericForm the numeric value (default = null)
 */
class Number(
  override val lemma: String,
  override val gender: Gender = Gender.Undefined,
  override val number: NumberProp = NumberProp.Undefined,
  val numericForm: kotlin.Number? = null
) : SingleMorphology(POS.Num), Thing, Genderable, Numerable, PersonDeclinable {

  /**
   * By default a Number is third person.
   */
  override val person: Person = Person.Third

  /**
   * @return the JSON object that represents this Number morphology
   */
  override fun toJSON(): JsonObject {

    val jsonObj: JsonObject = super.toJSON()

    jsonObj["numericForm"] = this.numericForm

    return jsonObj
  }
}
