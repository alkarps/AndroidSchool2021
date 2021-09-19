package ru.alkarps.android.school2021.hw03

import ru.alkarps.android.school2021.hw03.LiteralRepository.RUSSIAN_LITERALS
import ru.alkarps.android.school2021.hw03.LiteralRepository.TRANSLIT_LITERALS

object TranslateService {
    fun translate(chars: CharSequence) = when {
        chars.isBlank() -> chars.toString()
        isRussianLiterals(chars.first { it != ' ' }) -> toTranslit((chars))
        else -> toRussian(chars)
    }

    private fun isRussianLiterals(char: Char): Boolean {
        return RUSSIAN_LITERALS.hasLiteral(char.uppercaseChar())
    }

    private fun toTranslit(chars: CharSequence): String {
        return chars.map {
            val lowerCase = it.isLowerCase()
            val index = RUSSIAN_LITERALS.getIndex(it.uppercaseChar())
            TRANSLIT_LITERALS.getLiteral(index).let { if (lowerCase) it.lowercase() else it }
        }.joinToString("")
    }

    private fun toRussian(chars: CharSequence): String {
        return chars.toString()
    }
}