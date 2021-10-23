package ru.alkarps.android.school2021.hw18.domen.language

import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Репозиторий языков
 */
interface LanguageRepository {
    /**
     * Метод получения текущего языка
     *
     * @return текущий язык или null, если такого нет
     */
    fun getCurrentLanguage(): Language?
    /**
     * Метод получения всех доступных языков
     *
     * @return список доступных языков или null, если такого нет
     */
    fun getLanguages():List<Language>?
}