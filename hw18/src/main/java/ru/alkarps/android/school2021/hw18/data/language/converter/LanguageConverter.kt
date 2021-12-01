package ru.alkarps.android.school2021.hw18.data.language.converter

import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity
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

    /**
     * Метод конвертации из сущностей БД
     *
     * @param language список полученных сущностей БД доступных языков
     * @return список доступных языков
     */
    fun fromEntity(language: LanguageEntity): Language

    /**
     * Метод конвертации в сущность БД
     *
     * @param languages список полученных доступных языков
     * @return список сущностей БД доступных языков
     */
    fun toEntity(languages: List<Language>): List<LanguageEntity>
}