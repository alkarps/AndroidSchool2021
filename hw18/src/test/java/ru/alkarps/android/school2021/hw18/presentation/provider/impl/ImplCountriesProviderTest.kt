package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.DivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter

class ImplCountriesProviderTest {
    @Test
    fun getCountries() {
        val countryService = mockk<CountryService>()
        val countryConverter = mockk<CountryConverter>()
        val countries = listOf(Country("", "", emptyList(), "", emptyList()))
        every { countryService.getCountries() } returns countries
        val expected = listOf(CountryView(DivisionView("", ""), emptyList()))
        every { countryConverter.countriesToView(any()) } returns expected

        ImplCountriesProvider(countryService, countryConverter).getCountries()
            .test().assertValue(expected)

        verify { countryService.getCountries() }
        verify { countryConverter.countriesToView(countries) }
    }
}