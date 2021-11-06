package ru.alkarps.android.school2021.hw18.domen.country

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Сервис для работы с странами и территориальными подразделениями
 */
interface CountryService {
    /**
     * Метод получения текущего выбранного территориального подразделения
     *
     * @return текущее выбранное территориальное подразделение
     */
    fun getCurrentSubdivision(): Subdivision

    /**
     * Метод получения списка доступных стран для получения праздников
     *
     * @return список стран
     */
    fun getCountries(): List<Country>

    /**
     * Метод получения доступных территориальныъ подразделений для текущей страны
     *
     * @return список доступных территориальных подразделений для текущей страны
     */
    fun getSubdivisionsForCurrentCountry(): List<Subdivision>

    /**
     * Метод получения доступных территориальных подразделений для страны
     *
     * @param countryCode код страны ([ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2))
     * @return список доступных территориальных подразделений
     *
     * @see Country
     */
    fun getSubdivisions(countryCode: String): List<Subdivision>
}