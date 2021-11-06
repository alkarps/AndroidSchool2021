package ru.alkarps.android.school2021.hw18.domen.country

import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision

/**
 * Клиент для получения доступных стран и территориальных подразделений
 */
interface CountryClient {
    /**
     * Метод получения доступных стран и их территориальных подразделений
     *
     * @return страны и их территориальные подразделения
     */
    fun getCountriesWithSubdivisions(): List<CountryWithSubdivision>
}