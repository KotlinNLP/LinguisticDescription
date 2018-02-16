/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.Relation
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.morphologies.Morphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Conjugable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.PersonDeclinable

/**
 * The 'verb' morphology.
 *
 * @property lemma the lemma
 * @property mood the 'mood' morphology property
 * @property tense the 'tense' morphology property
 * @property gender the 'gender' morphology property
 * @property number the 'number' morphology property
 * @property person the 'person' morphology property
 */
sealed class Verb(
  lemma: String,
  override val mood: Mood,
  override val tense: Tense,
  override val gender: Gender,
  override val number: Number,
  override val person: Person
) : Morphology(lemma), Relation, ContentWord, Conjugable, Genderable, Numerable, PersonDeclinable {

  /**
   * The 'verb' morphology.
   */
  class Base(lemma: String, mood: Mood, tense: Tense, gender: Gender, number: Number, person: Person)
    : Verb(lemma, mood, tense, gender, number, person) {

    override val type = MorphologyType.Verb
  }

  /**
   * The 'auxiliary verb' morphology.
   */
  class Auxiliary(lemma: String, mood: Mood, tense: Tense, gender: Gender, number: Number, person: Person)
    : Verb(lemma, mood, tense, gender, number, person) {

    override val type = MorphologyType.VerbAux
  }

  /**
   * The 'modal verb' morphology.
   */
  class Modal(lemma: String, mood: Mood, tense: Tense, gender: Gender, number: Number, person: Person)
    : Verb(lemma, mood, tense, gender, number, person) {

    override val type = MorphologyType.VerbModal
  }
}
