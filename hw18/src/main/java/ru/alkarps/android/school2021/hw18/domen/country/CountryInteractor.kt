package ru.alkarps.android.school2021.hw18.domen.country

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Сервис для работы с странами и территориальными подразделениями
 */
interface CountryInteractor {
    /**
     * Метод получения текущего выбранного территориального подразделения
     *
     * @return текущее выбранное территориальное подразделение
     */
    fun getCurrentSubdivision(): Subdivision

    /**
     * Метод получения списка доступных стран и их ТП для получения праздников
     *
     * @return список стран и их ТП
     */
    fun getCountries(): List<Country>
}