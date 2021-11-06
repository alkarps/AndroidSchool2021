package ru.alkarps.android.school2021.hw18.data.language.converter

import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Конвертер представления языков в дата слое в представление языков бизнес слоя
 */
interface LanguageConverter {
    /**
     * Метод конвертации
     *
     * @param languages список полученных доступных языков
     * @return список доступных языков
     */
    fun fromDto(languages: List<LanguageDTO>): List<Language>
}