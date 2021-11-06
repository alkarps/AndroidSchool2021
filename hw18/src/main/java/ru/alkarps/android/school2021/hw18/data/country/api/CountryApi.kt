package ru.alkarps.android.school2021.hw18.data.country.api

import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO

/**
 * API для вызове сервиса HolidayApi для получения доступных стран и подразделений
 *
 */
interface CountryApi {
    /**
     * Метод получения доступных стран
     *
     * @return список доступных стран
     */
    fun getCountries():List<CountryDTO>
}