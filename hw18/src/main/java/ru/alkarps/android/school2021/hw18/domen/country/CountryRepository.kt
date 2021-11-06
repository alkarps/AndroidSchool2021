package ru.alkarps.android.school2021.hw18.domen.country

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Репозиторий стран и территориальных подразделений
 */
interface CountryRepository {
    /**
     * Метод получения списка доступных стран
     *
     * @return список стран или null, если их нет
     */
    fun getCountries(): List<Country>?

    /**
     * Метод получения территориальных подразделений страны
     *
     * @param countryCode код страны ([ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2))
     * @return список доступных подразделений
     */
    fun getSubdivisions(countryCode: String): List<Subdivision>?

    /**
     * Метод поиска территориальных подразделений страны
     *
     * @param subdivisionCode [ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2) код территориального подразделения
     * @return территориальное подразделение или null, если оно не найдено
     */
    fun findSubdivision(subdivisionCode: String): Subdivision?

    /**
     * Метод сохранения стран и их территориальных подразделений
     *
     * @param countriesWithSubdivision страны и их территориальные подразделения
     */
    fun saveCountryWithSubdivisions(countriesWithSubdivision: List<CountryWithSubdivision>)
}