package ru.alkarps.android.school2021.hw18.data.language.storage

import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Хранилище доступных языков
 */
interface LanguageStorage {
    /**
     * Метод получения всех доступных языков
     *
     * @return список доступных языков или null, в случае их отсутствия в хранилище
     */
    fun getLanguages(): List<Language>?

    /**
     * Сохранение языков с хранилище
     *
     * @param languages список доступных языков
     */
    fun saveLanguages(languages: List<Language>)

    /**
     * Поиск языка по
     *
     * @param code
     * @return
     */
    fun findLanguage(code: String): Language?
}