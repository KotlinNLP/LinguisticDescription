/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.morphology.morphologies

import com.kotlinnlp.linguisticdescription.morphology.morphologies.discourse.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.relations.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.*
import com.kotlinnlp.linguisticdescription.morphology.morphologies.things.Number
import com.kotlinnlp.linguisticdescription.morphology.properties.MorphologyProperty
import com.kotlinnlp.linguisticdescription.morphology.MorphologyType
import com.kotlinnlp.linguisticdescription.utils.MissingMorphologyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

/**
 * The factory of a new [Morphology].
 */
object MorphologyFactory {

  /**
   * The map of morphology types to the related k-classes.
   */
  private val morphologyClasses = mapOf<MorphologyType, KClass<*>>(

    MorphologyType.Adj to Adjective.Base::class,
    MorphologyType.AdjCompar to Adjective.Comparative::class,
    MorphologyType.AdjDeict to Adjective.Deictic::class,
    MorphologyType.AdjDemons to Adjective.Demonstrative.Base::class,
    MorphologyType.AdjDemonsAntec to Adjective.Demonstrative.Antecedent::class,
    MorphologyType.AdjDemonsSucc to Adjective.Demonstrative.Successive::class,
    MorphologyType.AdjExclam to Adjective.Exclamative::class,
    MorphologyType.AdjIndef to Adjective.Indefinite.Base::class,
    MorphologyType.AdjIndefDistr to Adjective.Indefinite.Distributive::class,
    MorphologyType.AdjIndefQuant to Adjective.Indefinite.Quantifying::class,
    MorphologyType.AdjIndefSubord to Adjective.Indefinite.Subordinating::class,
    MorphologyType.AdjInterr to Adjective.Interrogative::class,
    MorphologyType.AdjPoss to Adjective.Possessive::class,
    MorphologyType.AdjQualif to Adjective.Qualifying.Base::class,
    MorphologyType.AdjQualifPost to Adjective.Qualifying.Postpositive::class,
    MorphologyType.AdjOrdin to Adjective.Ordinal::class,
    MorphologyType.AdjRelat to Adjective.Relative::class,

    MorphologyType.AdvAdvers to Adverb.Adversative::class,
    MorphologyType.AdvCompar to Adverb.Comparative::class,
    MorphologyType.AdvConcess to Adverb.Concessive::class,
    MorphologyType.AdvDeict to Adverb.Deictic::class,
    MorphologyType.AdvIndef to Adverb.Indefinite::class,
    MorphologyType.AdvIndefSubord to Adverb.IndefiniteSubordinating::class,
    MorphologyType.AdvInterr to Adverb.Interrogative::class,
    MorphologyType.AdvLimit to Adverb.Limiting::class,
    MorphologyType.AdvLoc to Adverb.Locative::class,
    MorphologyType.AdvModal to Adverb.Modal::class,
    MorphologyType.AdvNeg to Adverb.Negative::class,
    MorphologyType.AdvPhras to Adverb.Phrase::class,
    MorphologyType.AdvQuant to Adverb.Quantitative::class,
    MorphologyType.AdvStreng to Adverb.Strength.Base::class,
    MorphologyType.AdvStrengNeg to Adverb.Strength.Negative::class,
    MorphologyType.AdvTime to Adverb.Time::class,

    MorphologyType.Art to Article.Base::class,
    MorphologyType.ArtDef to Article.Definite::class,
    MorphologyType.ArtIndef to Article.Indefinite.Base::class,
    MorphologyType.ArtIndefPart to Article.Indefinite.Partitive::class,

    MorphologyType.Conj to Conjunction.Base::class,
    MorphologyType.ConjCompar to Conjunction.Comparative.Base::class,
    MorphologyType.ConjComparAntec to Conjunction.Comparative.Antecedent::class,
    MorphologyType.ConjComparSucc to Conjunction.Comparative.Successive::class,
    MorphologyType.ConjConcess to Conjunction.Concessive::class,
    MorphologyType.ConjCoord to Conjunction.Coordinating.Base::class,
    MorphologyType.ConjCoordAdvers to Conjunction.Coordinating.Adversative::class,
    MorphologyType.ConjCoordDisj to Conjunction.Coordinating.Disjunctive::class,
    MorphologyType.ConjCoordExplic to Conjunction.Coordinating.Explicit::class,
    MorphologyType.ConjCoordNeg to Conjunction.Coordinating.Negative::class,
    MorphologyType.ConjCorrel to Conjunction.Correlating.Base::class,
    MorphologyType.ConjCorrelAntec to Conjunction.Correlating.Antecedent::class,
    MorphologyType.ConjCorrelSucc to Conjunction.Correlating.Successive::class,
    MorphologyType.ConjSubord to Conjunction.Subordinating.Base::class,
    MorphologyType.ConjSubordAdvers to Conjunction.Subordinating.Adversative::class,
    MorphologyType.ConjSubordInterr to Conjunction.Subordinating.Interrogative::class,

    MorphologyType.Noun to Noun.Base::class,
    MorphologyType.NounCommon to Noun.Common.Base::class,
    MorphologyType.NounCommonGerund to Noun.Common.Gerundive::class,
    MorphologyType.NounCommonQuant to Noun.Common.Quantifying::class,
    MorphologyType.NounProper to Noun.Proper.Base::class,
    MorphologyType.NounProperLoc to Noun.Proper.Location::class,
    MorphologyType.NounProperOrg to Noun.Proper.Organization::class,
    MorphologyType.NounProperPer to Noun.Proper.Person::class,

    MorphologyType.Pron to Pronoun.Base::class,
    MorphologyType.PronDemons to Pronoun.Demonstrative::class,
    MorphologyType.PronExclam to Pronoun.Exclamative::class,
    MorphologyType.PronIndef to Pronoun.Indefinite.Base::class,
    MorphologyType.PronIndefDistr to Pronoun.Indefinite.Distributive::class,
    MorphologyType.PronIndefQuant to Pronoun.Indefinite.Quantifying::class,
    MorphologyType.PronIndefSubord to Pronoun.Indefinite.Subordinating::class,
    MorphologyType.PronInterr to Pronoun.Interrogative::class,
    MorphologyType.PronOrdin to Pronoun.Ordinal::class,
    MorphologyType.PronPers to Pronoun.Personal.Base::class,
    MorphologyType.PronPersEnclit to Pronoun.Personal.Enclitic::class,
    MorphologyType.PronPersProclit to Pronoun.Personal.Proclitic.Base::class,
    MorphologyType.PronPersProclitRefl to Pronoun.Personal.Proclitic.Reflexive::class,
    MorphologyType.PronPersProclitVariant to Pronoun.Personal.Proclitic.Variant::class,
    MorphologyType.PronPersRefl to Pronoun.Personal.Reflexive::class,
    MorphologyType.PronPersVariant to Pronoun.Personal.Variant::class,
    MorphologyType.PronPoss to Pronoun.Possessive::class,
    MorphologyType.PronRelat to Pronoun.Relative.Base::class,
    MorphologyType.PronRelatDouble to Pronoun.Relative.Double::class,

    MorphologyType.Prep to Preposition.Base::class,
    MorphologyType.PrepArt to Preposition.Articulated::class,
    MorphologyType.PrepPoss to Preposition.Possessive::class,

    MorphologyType.Postpos to Postposition.Base::class,
    MorphologyType.PostposPoss to Postposition.Possessive::class,

    MorphologyType.Interj to Interjection::class,
    MorphologyType.PhrasAff to Phrase.Affirmative::class,
    MorphologyType.PhrasExclam to Phrase.Exclamative::class,
    MorphologyType.PhrasInterr to Phrase.Interrogative::class,
    MorphologyType.PhrasNeg to Phrase.Negative::class,
    MorphologyType.Punct to Punctuation::class,

    MorphologyType.Verb to Verb.Base::class,
    MorphologyType.VerbAux to Verb.Auxiliary::class,
    MorphologyType.VerbModal to Verb.Modal::class,

    MorphologyType.Date to Date::class,
    MorphologyType.Hour to Hour::class,
    MorphologyType.Num to Number::class,
    MorphologyType.Predet to Predeterminer::class
  )

  /**
   * Create a new [Morphology] given its [properties].
   *
   * @param lemma the lemma of the morphology
   * @param type the morphology type
   * @param properties the map of property names to their values (optional, unnecessary adding properties are ignored)
   *
   * @throws MissingMorphologyProperty when a required property is missing
   *
   * @return a new morphology
   */
  operator fun invoke(lemma: String,
                      type: MorphologyType,
                      properties: Map<String, MorphologyProperty> = mapOf()): Morphology {

    val kClass: KClass<*> = morphologyClasses[type]!!
    val constructor: KFunction<Any> = kClass.constructors.last()

    val keywordArgs: Map<KParameter, Any?> = constructor.parameters.associate {

      val propertyName: String = it.name!!
      val isLemma: Boolean = propertyName == "lemma"

      if (!isLemma && propertyName !in properties) throw MissingMorphologyProperty(propertyName)

      Pair(it, if (isLemma) lemma else properties[propertyName]!!)
    }

    return constructor.callBy(keywordArgs) as Morphology
  }
}
