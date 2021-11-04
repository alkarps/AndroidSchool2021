package ru.alkarps.android.school2021.hw18.data.language.converter.impl

import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Реализация [LanguageConverter]
 */
class ImplLanguageConverter : LanguageConverter {
    override fun fromDto(languages: List<LanguageDTO>): List<Language> =
        languages.map { Language(it.code, it.name) }
}