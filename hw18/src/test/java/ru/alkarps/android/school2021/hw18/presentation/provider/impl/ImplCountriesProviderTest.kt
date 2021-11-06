package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter

class ImplCountriesProviderTest {
    private lateinit var countryService: CountryService
    private lateinit var countryConverter: CountryConverter
    private lateinit var testProvider: CountriesProvider

    @Before
    fun setUp() {
        countryService = mockk()
        countryConverter = mockk()
        testProvider = ImplCountriesProvider(countryService, countryConverter)
    }

    @Test
    fun getCountries() {
        val countries = listOf(Country("", "", emptyList(), ""))
        every { countryService.getCountries() } returns countries
        val expected = listOf(CountryView("", ""))
        every { countryConverter.countriesToView(any()) } returns expected

        testProvider.getCountries().test().assertValue(expected)

        verify { countryService.getCountries() }
        verify { countryConverter.countriesToView(countries) }
    }

    @Test
    fun getSubdivisionsForCurrentCountry() {
        val subdivisions = listOf(Subdivision("", "", emptyList()))
        every { countryService.getSubdivisionsForCurrentCountry() } returns subdivisions
        val expected = listOf(SubdivisionView("", ""))
        every { countryConverter.subdivisionToView(any()) } returns expected

        testProvider.getSubdivisionsForCurrentCountry().test().assertValue(expected)

        verify { countryService.getSubdivisionsForCurrentCountry() }
        verify { countryConverter.subdivisionToView(subdivisions) }
    }

    @Test
    fun getSubdivisions() {
        val countryCode = "CountryCode"
        val subdivisions = listOf(Subdivision("", "", emptyList()))
        every { countryService.getSubdivisions(any()) } returns subdivisions
        val expected = listOf(SubdivisionView("", ""))
        every { countryConverter.subdivisionToView(any()) } returns expected

        testProvider.getSubdivisions(countryCode).test().assertValue(expected)

        verify { countryService.getSubdivisions(countryCode) }
        verify { countryConverter.subdivisionToView(subdivisions) }
    }
}