package ru.alkarps.android.school2021.hw18.data.country.converter

import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryWithSubdivisionsEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Конвертер представления стран и территориальных подразделений в дата слое
 * в представление стран и территориальных подразделений бизнес слоя
 */
interface CountryConverter {
    /**
     * Метод конвертации из DTO
     *
     * @param countries список полученных доступных стран и территориальных подразделений
     * @return список доступных стран и территориальных подразделений
     */
    fun fromDto(countries: List<CountryDTO>): List<Country>

    /**
     * Метод конвертации из сущности БД
     *
     * @param country описание сущностей БД страны с территориальными подразделениями
     * @return доменное представление страны и территориальных подразделений
     */
    fun fromEntity(country: CountryWithSubdivisionsEntity): Country

    /**
     * Метод конвертации из сущности БД
     *
     * @param subdivision описание сущностей БД территориального подразделения
     * @return доменное представление территориального подразделения
     */
    fun fromEntity(subdivision: SubdivisionEntity): Subdivision

    /**
     * Метод конвертации в сущности БД
     *
     * @param country доменное представление страны и территориальных подразделений
     * @return описание сущностей БД страны с территориальными подразделениями
     */
    fun toEntity(country: Country): CountryWithSubdivisionsEntity
}