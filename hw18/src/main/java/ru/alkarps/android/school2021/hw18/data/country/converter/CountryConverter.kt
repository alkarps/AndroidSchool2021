package ru.alkarps.android.school2021.hw18.data.country.converter

import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision

/**
 * Конвертер представления стран и территориальных подразделений в дата слое
 * в представление стран и территориальных подразделений бизнес слоя
 */
interface CountryConverter {
    /**
     * Метод конвертации
     *
     * @param countries список полученных доступных стран и территориальных подразделений
     * @return список доступных стран и территориальных подразделений
     */
    fun fromDto(countries: List<CountryDTO>): List<CountryWithSubdivision>
}