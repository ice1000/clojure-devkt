package org.intellij.clojure.devkt

import org.ice1000.devkt.openapi.*
import org.intellij.clojure.devkt.lang.*
import org.intellij.clojure.devkt.psi.ClojureTypes.*
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType

class Clojure<TextAttributes> : ExtendedDevKtLanguage<TextAttributes>(
		ClojureLanguage,
		ClojureParserDefinition
) {
	override val lineCommentStart: String get() = ";"
	override fun satisfies(fileName: String) = fileName == "build.boot" ||
			fileName.endsWith(".clj") ||
			fileName.endsWith(".cljs") ||
			fileName.endsWith(".cljc")

	override fun attributesOf(type: IElementType, colorScheme: ColorScheme<TextAttributes>) = when (type) {
		ClojureTokens.LINE_COMMENT -> colorScheme.lineComments
		C_STRING -> colorScheme.string
		C_CHAR -> colorScheme.charLiteral
		C_NUMBER, C_HEXNUM, C_RDXNUM, C_RATIO -> colorScheme.numbers
		C_BOOL -> colorScheme.keywords
		C_NIL -> colorScheme.keywords
		C_DOT, C_DOTDASH -> colorScheme.keywords
		C_COLONCOLON -> colorScheme.keywords
		C_COLON -> colorScheme.colon
		C_SYM -> colorScheme.identifiers
		C_COMMA -> colorScheme.comma
		C_SLASH -> colorScheme.operators
		C_SYNTAX_QUOTE -> colorScheme.operators
		C_QUOTE -> colorScheme.string
		C_TILDE, C_TILDE_AT -> colorScheme.operators
		C_AT -> colorScheme.operators
		// C_HAT, C_SHARP_HAT -> colorScheme.metadata
		// C_SHARP, C_SHARP_COMMENT, C_SHARP_EQ, C_SHARP_NS -> pack(ClojureColors.READER_MACRO)
		// C_SHARP_QMARK, C_SHARP_QMARK_AT, C_SHARP_QUOTE -> pack(ClojureColors.READER_MACRO)
		C_PAREN1, C_PAREN2 -> colorScheme.parentheses
		C_BRACE1, C_BRACE2 -> colorScheme.braces
		C_BRACKET1, C_BRACKET2 -> colorScheme.brackets
		else -> null
	}

	override fun annotate(element: PsiElement, document: AnnotationHolder<TextAttributes>, colorScheme: ColorScheme<TextAttributes>) {
		// TODO
	}
}
