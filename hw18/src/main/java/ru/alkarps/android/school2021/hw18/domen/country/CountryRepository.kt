package ru.alkarps.android.school2021.hw18.domen.country

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Репозиторий стран и территориальных подразделений
 */
interface CountryRepository {
    /**
     * Метод получения списка доступных стран и их территориальных подразделений
     *
     * @return список стран или null, если их нет
     */
    fun getCountries(): List<Country>?

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
     * @param countries страны и их территориальные подразделения
     */
    fun saveCountries(countries: List<Country>)
}