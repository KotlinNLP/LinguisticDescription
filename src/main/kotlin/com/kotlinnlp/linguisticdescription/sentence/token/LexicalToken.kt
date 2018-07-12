package com.kotlinnlp.linguisticdescription.sentence.token

import com.kotlinnlp.linguisticdescription.morphology.Morphology

/**
 * The LexicalToken.
 */
interface LexicalToken : Token {

  /**
   * The list of lexical interpretations (lemma and morphological properties)
   */
  val lexicalForms: List<Morphology>
}
