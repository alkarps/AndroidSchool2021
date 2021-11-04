package ru.alkarps.android.school2021.hw18.data.language.api

import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
/**
 * API для вызове сервиса HolidayApi для получения доступных языков
 */
interface LanguageApi {
    /**
     * Метод получения доступных языков
     *
     * @return список доступных языков
     */
    fun getLanguages(): List<LanguageDTO>
}