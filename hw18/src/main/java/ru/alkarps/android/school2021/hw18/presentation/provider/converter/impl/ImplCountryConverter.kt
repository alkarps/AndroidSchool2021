package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter
import javax.inject.Inject

/**
 * Реализация [CountryConverter]
 *
 * @constructor Новый объект реализации [CountryConverter]
 */
class ImplCountryConverter @Inject constructor() : CountryConverter {
    override fun countriesToView(countries: List<Country>): List<CountryView> =
        countries.map { CountryView(it.code, it.name) }

    override fun subdivisionToView(subdivisions: List<Subdivision>): List<SubdivisionView> =
        subdivisions.map { SubdivisionView(it.code, it.name) }
}