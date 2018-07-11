package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.morphology.MorphologyEntry

/**
 * The LexicalToken.
 */
interface LexicalToken : Token {

  /**
   * The list of lexical interpretations (lemma and morphological properties)
   */
  val lexicalForms: List<MorphologyEntry>
}