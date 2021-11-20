package ru.alkarps.android.school2021.hw18.data.country

import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.model.Country
import javax.inject.Inject

/**
 * Реализация [CountryClient]
 *
 * @property api апи для вызова HolidayApi
 * @property converter конвертер моделей
 */
class ImplCountryClient @Inject constructor(
    private val api: CountryApi,
    private val converter: CountryConverter
) : CountryClient {
    override fun getCountries(): List<Country> = api.getCountries().let { converter.fromDto(it) }
}