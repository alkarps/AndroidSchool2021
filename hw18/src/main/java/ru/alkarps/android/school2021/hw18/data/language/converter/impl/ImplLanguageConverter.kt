package ru.alkarps.android.school2021.hw18.data.language.converter.impl

import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity
import ru.alkarps.android.school2021.hw18.domen.model.Language
import javax.inject.Inject

/**
 * Реализация [LanguageConverter]
 */
class ImplLanguageConverter @Inject constructor() : LanguageConverter {
    override fun fromDto(languages: List<LanguageDTO>): List<Language> =
        languages.map { Language(it.code, it.name) }

    override fun fromEntity(languages: List<LanguageEntity>): List<Language> =
        languages.map { fromEntity(it) }

    override fun fromEntity(language: LanguageEntity): Language =
        Language(language.code, language.name)

    override fun toEntity(languages: List<Language>): List<LanguageEntity> =
        languages.map { LanguageEntity(it.code.lowercase(), it.name) }
}