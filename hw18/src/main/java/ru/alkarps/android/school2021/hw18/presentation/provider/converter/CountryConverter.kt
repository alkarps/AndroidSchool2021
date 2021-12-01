package ru.alkarps.android.school2021.hw18.presentation.provider.converter

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView

/**
 * Конвертер [Country] и [Subdivision] в модель данных UI
 */
interface CountryConverter {
    /**
     * Метод конвертации
     *
     * @param countries страны и их ТП
     * @return страны и их ТП для отображения на UI
     */
    fun countriesToView(countries: List<Country>): List<CountryView>
}