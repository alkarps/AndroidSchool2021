package ru.alkarps.android.school2021.hw03

object LiteralRepository {
    val RUSSIAN_LITERALS = Literals(
        listOf(
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
            'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ы', 'Ь', 'Ъ', 'Э', 'Ю', 'Я', ' '
        )
    )

    val TRANSLIT_LITERALS = Literals(
        listOf(
            "A", "B", "V", "G", "D", "E", "E", "ZH", "Z", "I", "I", "K", "L", "M", "N", "O", "P",
            "R", "S", "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "Y", "", "IE", "E", "IU",
            "IA", " "
        )
    )
}