package ru.alkarps.android.school2021.hw18.domen.language

import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Клиент для получения доступных языков
 */
interface LanguageClient {
    /**
     * Метод получения всех доступных языков
     *
     * @return список доступных языков
     */
    fun getLanguages():List<Language>
}