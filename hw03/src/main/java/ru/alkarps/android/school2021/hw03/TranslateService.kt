package ru.alkarps.android.school2021.hw03

object TranslateService {
    private val RUSSIAN_LITERALS = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
        'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ы', 'Ь', 'Ъ', 'Э', 'Ю', 'Я', ' '
    )
    private val TRANSLIT_LITERALS = listOf(
        "A", "B", "V", "G", "D", "E", "E", "ZH", "Z", "I", "I", "K", "L", "M", "N", "O", "P",
        "R", "S", "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "Y", "", "IE", "E", "IU",
        "IA", " "
    )

    fun translate(chars: CharSequence) = when {
        chars.isBlank() -> chars.toString()
        isRussianLiterals(chars.first { it != ' ' }) -> toTranslit((chars))
        else -> toRussian(chars)
    }

    private fun isRussianLiterals(char: Char): Boolean {
        return RUSSIAN_LITERALS.contains(char.uppercaseChar())
    }

    private fun toTranslit(chars: CharSequence): String {
        return chars.map {
            val lowerCase = it.isLowerCase()
            val index = RUSSIAN_LITERALS.indexOf(it.uppercaseChar())
            TRANSLIT_LITERALS[index].let { if (lowerCase) it.lowercase() else it }
        }.joinToString("")
    }

    private fun toRussian(chars: CharSequence): String {
        val russian = mutableListOf<Char>()
        var bufferLiteral = ""
        var lowerCase = false
        for (char in chars) {
            lowerCase = lowerCase || char.isLowerCase()
            bufferLiteral = "$bufferLiteral$char"
            val buffer = TRANSLIT_LITERALS.filter { it.startsWith(bufferLiteral.uppercase()) }
            if (buffer.isEmpty()) throw IllegalArgumentException("Ошибка в тексте")
            if (buffer.size == 1 && buffer.contains(bufferLiteral.uppercase())) {
                val index = TRANSLIT_LITERALS.indexOf(bufferLiteral.uppercase())
                russian.add(RUSSIAN_LITERALS[index].let { if (lowerCase) it.lowercaseChar() else it })
                bufferLiteral = ""
                lowerCase = false
            }
        }
        if (bufferLiteral.isNotBlank()) {
            if (TRANSLIT_LITERALS.contains(bufferLiteral.uppercase())) {
                val index = TRANSLIT_LITERALS.indexOf(bufferLiteral.uppercase())
                russian.add(RUSSIAN_LITERALS[index].let { if (lowerCase) it.lowercaseChar() else it })
            } else throw IllegalArgumentException("Ошибка в тексте")
        }
        return russian.joinToString("")
    }
}