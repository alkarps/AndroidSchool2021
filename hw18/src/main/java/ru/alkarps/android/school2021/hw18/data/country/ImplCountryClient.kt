package ru.alkarps.android.school2021.hw18.data.country

import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision

/**
 * Реализация [CountryClient]
 *
 * @property api апи для вызова HolidayApi
 * @property converter конвертер моделей
 */
class ImplCountryClient(
    private val api: CountryApi,
    private val converter: CountryConverter
) : CountryClient {
    override fun getCountriesWithSubdivisions(): List<CountryWithSubdivision> =
        api.getCountries().let { converter.fromDto(it) }
}