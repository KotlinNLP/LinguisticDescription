/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.things

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.CaseDeclinable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.PersonDeclinable

/**
 * The 'predeterminer' morphology.
 *
 * @property lemma the lemma
 * @property gender the 'gender' morphological property
 * @property number the 'number' morphological property
 * @property person the 'person' morphological property
 * @property case the 'grammatical case' morphological property
 */
class Predeterminer(
  override val lemma: String,
  override val gender: Gender = Gender.Undefined,
  override val number: Number = Number.Undefined,
  override val person: Person = Person.Undefined,
  override val case: GrammaticalCase = GrammaticalCase.Undefined
) : SingleMorphology(POS.Predet), Thing, Genderable, Numerable, PersonDeclinable, CaseDeclinable
