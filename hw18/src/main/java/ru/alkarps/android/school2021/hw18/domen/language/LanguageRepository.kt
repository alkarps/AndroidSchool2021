package ru.alkarps.android.school2021.hw18.domen.language

import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Репозиторий языков
 */
interface LanguageRepository {
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
     * Поиск языка по коду
     *
     * @param code код языка
     * @return язык или null, если он не найден
     */
    fun findLanguage(code: String): Language?
}