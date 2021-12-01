package ru.alkarps.android.school2021.hw18.data.country

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.domen.model.Country

class ImplCountryClientTest {
    @Test
    fun getCountriesWithSubdivisions() {
        val countries = listOf(CountryDTO("", "", emptyMap(), emptyList(), "", emptyList()))
        val expected = listOf(Country("", "", emptyList(), "", emptyList()))
        val api = mockk<CountryApi>()
        every { api.getCountries() } returns countries
        val converter = mockk<CountryConverter>()
        every { converter.fromDto(any()) } returns expected

        assertThat(ImplCountryClient(api, converter).getCountries()).isEqualTo(expected)

        verify { api.getCountries() }
        verify { converter.fromDto(countries) }
    }
}