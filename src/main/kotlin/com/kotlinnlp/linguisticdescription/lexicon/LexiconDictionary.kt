/* Copyright 2017-present The KotlinNLP Authors. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * ------------------------------------------------------------------*/

package com.kotlinnlp.linguisticdescription.lexicon

import com.beust.klaxon.*
import com.kotlinnlp.linguisticdescription.lexicon.liwc.LIWCCategory
import com.kotlinnlp.utils.forEachLine
import com.kotlinnlp.utils.toInputStream
import java.io.Serializable

/**
 * A lexicon dictionary.
 */
class LexiconDictionary : Serializable {

  /**
   * An lexical entry of a dictionary entry, associated to a POS tag.
   *
   * @property sentiment the sentiment info of the entry (can be null)
   * @property syntax the syntactic info of the entry (can be null)
   * @property semantic the semantic info of the entry (can be null)
   */
  data class LexicalEntry(
    val sentiment: SentimentInfo?,
    val syntax: SyntacticInfo?,
    val semantic: SemanticInfo?
  ) : Serializable

  /**
   * Sentiment info.
   *
   * @property polarity the polarity (a value in the range [-1.0, 1.0])
   * @property categories a list of LIWC categories (can be null)
   */
  data class SentimentInfo(val polarity: Double, val categories: List<LIWCCategory>?) : Serializable

  /**
   * Syntactic info.
   *
   * @property subcategorization a list of syntactic categories
   * @property regencies a list of regencies (can be null)
   */
  data class SyntacticInfo(val subcategorization: List<String>?, val regencies: List<String>?) : Serializable {

    /**
     * Check requirements.
     */
    init {
      require(this.subcategorization != null || this.regencies != null) {
        "At least one between the subcategorization and the regencies must be not null."
      }
    }
  }

  /**
   * Semantic info.
   *
   * @property analogy a list of terms that are semantically analogous (can be null)
   * @property classes a list of semantic classes (can be null)
   */
  data class SemanticInfo(val analogy: List<String>?, val classes: List<SemanticClass>?) : Serializable {

    /**
     * Check requirements.
     */
    init {
      require(this.analogy != null || this.classes != null) {
        "At least one between the analogy and the classes must be not null."
      }
    }
  }

  /**
   * A semantic class.
   *
   * @property type the class type
   * @property name the class name
   */
  data class SemanticClass(val type: String, val name: String) : Serializable

  companion object {

    /**
     * A map of LIWC categories associated by annotation.
     */
    private val annotationsToLIWCCategories: Map<String, LIWCCategory> =
      LIWCCategory.values().associateBy { it.annotation }

    /**
     * Load a [LexiconDictionary] from a JSONL file.
     *
     * @param filename the JSONL file name
     *
     * @return a new lexicon dictionary
     */
    fun load(filename: String): LexiconDictionary {

      val dictionary = LexiconDictionary()
      val jsonParser = Parser()

      forEachLine(filename) { line ->

        val entryObj: JsonObject = jsonParser.parse(line.toInputStream()) as JsonObject
        val lemma: String = entryObj.string("lemma")!!
        val lexicon: Map<String, Any?> = entryObj.obj("properties")!!.toMap()

        dictionary.lexiconMap[lemma] = lexicon.entries.associate {
          Pair(it.key, this.buildLexicalEntry(it.value as JsonObject))
        }
      }

      return dictionary
    }

    /**
     * @param jsonObject the JSON object that represents a lexical entry
     *
     * @return a lexical entry
     */
    private fun buildLexicalEntry(jsonObject: JsonObject): LexicalEntry {

      val sentiment: JsonObject? = jsonObject.obj("sentiment")
      val syntax: JsonObject? = jsonObject.obj("syntax")
      val semantic: JsonObject? = jsonObject.obj("semantic")

      return LexicalEntry(
        sentiment = this.buildSentimentInfo(sentiment),
        syntax = this.buildSyntacticInfo(syntax),
        semantic = this.buildSemanticInfo(semantic)
      )
    }

    /**
     * @param sentiment the JsonObject that contains sentiment info
     *
     * @return a sentiment info (can be null)
     */
    private fun buildSentimentInfo(sentiment: JsonObject?): SentimentInfo? {

      return if (sentiment != null) SentimentInfo(
        polarity = sentiment.double("polarity")!!,
        categories = sentiment
          .array<String>("categories")?.map { this.annotationsToLIWCCategories.getValue(it) })
      else
        null
    }

    /**
     * @param syntax the JsonObject that contains syntactic info
     *
     * @return a syntactic info (can be null)
     */
    private fun buildSyntacticInfo(syntax: JsonObject?): SyntacticInfo? {

      return if (syntax != null)
        SyntacticInfo(
          subcategorization = syntax.array<String>("subcategorization")?.toList(),
          regencies = syntax.array<String>("regencies")?.toList())
      else
        null
    }

    /**
     * @param semantic the JsonObject that contains semantic info
     *
     * @return a semantic info (can be null)
     */
    private fun buildSemanticInfo(semantic: JsonObject?): SemanticInfo? {

      val semanticClasses = semantic?.array<JsonArray<String>>("classes")
      val semanticAnalogy = semantic?.array<String>("analogy")

      return if (semantic != null && (semanticClasses != null || semanticAnalogy != null))
        SemanticInfo(
          classes = semanticClasses?.map { SemanticClass(type = it[0], name = it[1]) },
          analogy = semanticAnalogy?.toList())
      else
        null
    }
  }

  /**
   * Lexical entries associated by POS tags, in turn associated by lemma.
   */
  private val lexiconMap: MutableMap<String, Map<String, LexicalEntry>> = mutableMapOf()

  /**
   * Get a lexical entry by lemma and POS tag.
   *
   * @param lemma a lemma
   * @param posTag a POS tag annotation
   *
   * @return the lexical entry associated to the given [lemma] and [posTag] or null if no one has been found
   */
  fun get(lemma: String, posTag: String): LexicalEntry? = this.lexiconMap[lemma]?.get(posTag)
}
