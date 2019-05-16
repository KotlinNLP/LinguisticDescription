/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies.relations

import com.kotlinnlp.linguisticdescription.morphology.POS
import com.kotlinnlp.linguisticdescription.morphology.morphologies.ContentWord
import com.kotlinnlp.linguisticdescription.morphology.SingleMorphology
import com.kotlinnlp.linguisticdescription.morphology.properties.*
import com.kotlinnlp.linguisticdescription.morphology.properties.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Conjugable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Genderable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.Numerable
import com.kotlinnlp.linguisticdescription.morphology.properties.interfaces.PersonDeclinable

/**
 * The 'verb' morphology.
 *
 * @param pos the POS of this morphology
 */
sealed class Verb(pos: POS) : SingleMorphology(pos), Relation, ContentWord, Conjugable, Genderable, Numerable, PersonDeclinable {

  /**
   * The 'verb' morphology.
   *
   * @property lemma the lemma
   * @property mood the 'mood' morphological property
   * @property tense the 'tense' morphological property
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   */
  class Base(
    override val lemma: String,
    override val mood: Mood = Mood.Base,
    override val tense: Tense = Tense.Base,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined
  ) : Verb(POS.Verb)

  /**
   * The 'auxiliary verb' morphology.
   *
   * @property lemma the lemma
   * @property mood the 'mood' morphological property
   * @property tense the 'tense' morphological property
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   */
  class Auxiliary(
    override val lemma: String,
    override val mood: Mood = Mood.Base,
    override val tense: Tense = Tense.Base,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined
  ) : Verb(POS.VerbAux)

  /**
   * The 'modal verb' morphology.
   *
   * @property lemma the lemma
   * @property mood the 'mood' morphological property
   * @property tense the 'tense' morphological property
   * @property gender the 'gender' morphological property
   * @property number the 'number' morphological property
   * @property person the 'person' morphological property
   */
  class Modal(
    override val lemma: String,
    override val mood: Mood = Mood.Base,
    override val tense: Tense = Tense.Base,
    override val gender: Gender = Gender.Undefined,
    override val number: Number = Number.Undefined,
    override val person: Person = Person.Undefined
  ) : Verb(POS.VerbModal)
}
