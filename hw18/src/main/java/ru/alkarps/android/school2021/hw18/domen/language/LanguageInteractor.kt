package ru.alkarps.android.school2021.hw18.domen.language

import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Сервис для работы с языками
 */
interface LanguageInteractor {
    /**
     * Метод получения текущего языка
     *
     * @return текущий язык
     */
    fun getCurrentLanguage(): Language
    /**
     * Метод получения всех доступных языков
     *
     * @return список доступных языков
     */
    fun getLanguages():List<Language>
}