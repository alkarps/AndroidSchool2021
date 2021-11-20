package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.DivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter
import javax.inject.Inject

/**
 * Реализация [CountryConverter]
 *
 * @constructor Новый объект реализации [CountryConverter]
 */
class ImplCountryConverter @Inject constructor() : CountryConverter {
    override fun countriesToView(countries: List<Country>): List<CountryView> =
        countries.map {
            CountryView(
                DivisionView(it.code, it.name),
                subdivisionToView(it.subdivisions)
            )
        }

    private fun subdivisionToView(subdivision: List<Subdivision>) =
        subdivision.map { DivisionView(it.code, it.name) }
}