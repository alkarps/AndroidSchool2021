package ru.alkarps.android.school2021.hw18.data.language.converter.impl

import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.domen.model.Language
import javax.inject.Inject

/**
 * Реализация [LanguageConverter]
 */
class ImplLanguageConverter @Inject constructor() : LanguageConverter {
    override fun fromDto(languages: List<LanguageDTO>): List<Language> =
        languages.map { Language(it.code, it.name) }
}