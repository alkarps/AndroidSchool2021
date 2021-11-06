package ru.alkarps.android.school2021.hw18.domen.country.impl

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.domen.country.impl.ImplCountryService.Companion.UNKNOWN_CODE
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

class ImplCountryServiceTest {
    private lateinit var settings: SettingsRepository
    private lateinit var repository: CountryRepository
    private lateinit var client: CountryClient
    private lateinit var testService: CountryService

    @Before
    fun setUp() {
        settings = mockk()
        repository = mockk()
        client = mockk()
        testService = ImplCountryService(settings, repository, client)
    }

    @Test
    fun getCurrentSubdivision_whenRepositoryHas_whenReturnIt() {
        val subdivisionCode = "SubdivisionCode"
        val expected = Subdivision(subdivisionCode, "Name", emptyList())
        every { settings.getCurrentSubdivisionCode() } returns subdivisionCode
        every { repository.findSubdivision(any()) } returns expected
        assertThat(testService.getCurrentSubdivision()).isEqualTo(expected)
        verify { settings.getCurrentSubdivisionCode() }
        verify { repository.findSubdivision(subdivisionCode) }
    }

    @Test
    fun getCurrentSubdivision_whenRemoteHas_whenReturnIt() {
        val subdivisionCode = "SubdivisionCode"
        val countryCode = "CountryCode"
        val expected = Subdivision(subdivisionCode, "Name", emptyList())
        val countriesWithSubdivision = listOf(
            CountryWithSubdivision(
                Country(countryCode, "Name", emptyList(), "flag"),
                listOf(expected)
            )
        )
        every { settings.getCurrentSubdivisionCode() } returns subdivisionCode
        every { repository.findSubdivision(any()) } returns null
        every { settings.getCurrentCountryCode() } returns countryCode
        every { client.getCountriesWithSubdivisions() } returns countriesWithSubdivision
        justRun { repository.saveCountryWithSubdivisions(any()) }
        assertThat(testService.getCurrentSubdivision()).isEqualTo(expected)
        verify { settings.getCurrentSubdivisionCode() }
        verify { repository.findSubdivision(subdivisionCode) }
        verify { settings.getCurrentCountryCode() }
        verify { client.getCountriesWithSubdivisions() }
        verify { repository.saveCountryWithSubdivisions(countriesWithSubdivision) }
    }

    @Test
    fun getCurrentSubdivision_whenCurrentSubdivisionNotFound_whenReturnUnknown() {
        val subdivisionCode = "SubdivisionCode"
        val countryCode = "CountryCode"
        val expected = Subdivision("another$subdivisionCode", "Name", emptyList())
        val countriesWithSubdivision = listOf(
            CountryWithSubdivision(
                Country(countryCode, "Name", emptyList(), "flag"),
                listOf(expected)
            )
        )
        every { settings.getCurrentSubdivisionCode() } returns subdivisionCode
        every { repository.findSubdivision(any()) } returns null
        every { settings.getCurrentCountryCode() } returns countryCode
        every { client.getCountriesWithSubdivisions() } returns countriesWithSubdivision
        justRun { repository.saveCountryWithSubdivisions(any()) }
        assertThat(testService.getCurrentSubdivision())
            .isEqualTo(Subdivision(UNKNOWN_CODE, UNKNOWN_CODE, emptyList()))
        verify { settings.getCurrentSubdivisionCode() }
        verify { repository.findSubdivision(subdivisionCode) }
        verify { settings.getCurrentCountryCode() }
        verify { client.getCountriesWithSubdivisions() }
        verify { repository.saveCountryWithSubdivisions(countriesWithSubdivision) }
    }

    @Test
    fun getCountries_whenCountriesIsExist_thenReturnIt() {
        val expected = listOf(Country("code", "name", emptyList(), "flag"))
        every { repository.getCountries() } returns expected
        assertThat(testService.getCountries()).isEqualTo(expected)
        verify { repository.getCountries() }
    }

    @Test
    fun getCountries_whenCountriesIsNotExist_thenCallRemoteAndReturnIt() {
        val country = Country("code", "name", emptyList(), "flag")
        val countriesWithSubdivision = listOf(CountryWithSubdivision(country, emptyList()))
        every { repository.getCountries() } returns null
        every { client.getCountriesWithSubdivisions() } returns countriesWithSubdivision
        justRun { repository.saveCountryWithSubdivisions(any()) }
        assertThat(testService.getCountries()).isEqualTo(listOf(country))
        verify { repository.getCountries() }
        verify { client.getCountriesWithSubdivisions() }
        verify { repository.saveCountryWithSubdivisions(countriesWithSubdivision) }
    }

    @Test
    fun getSubdivisionsForCurrentCountry_whenRepositoryHas_thenReturnIt() {
        val code = "currentCode"
        every { settings.getCurrentCountryCode() } returns code
        subdivisions_whenRepositoryHas_thenReturnIt(code) { testService.getSubdivisionsForCurrentCountry() }
        verify { settings.getCurrentCountryCode() }
    }

    @Test
    fun getSubdivisions_whenRepositoryHas_thenReturnIt() {
        val code = "Code"
        subdivisions_whenRepositoryHas_thenReturnIt(code) {
            testService.getSubdivisions(code)
        }
    }

    private fun subdivisions_whenRepositoryHas_thenReturnIt(
        countryCode: String,
        method: () -> List<Subdivision>
    ) {
        val expected = listOf(Subdivision(countryCode, "name", emptyList()))
        every { repository.getSubdivisions(any()) } returns expected
        assertThat(method()).isEqualTo(expected)
        verify { repository.getSubdivisions(countryCode) }
    }

    @Test
    fun getSubdivisionsForCurrentCountry_whenRemoteHas_thenReturnIt() {
        val code = "currentCode"
        every { settings.getCurrentCountryCode() } returns code
        subdivisions_whenRemoteHas_thenReturnIt(code) { testService.getSubdivisionsForCurrentCountry() }
        verify { settings.getCurrentCountryCode() }
    }

    @Test
    fun getSubdivisions_whenRemoteHas_thenReturnIt() {
        val code = "Code"
        subdivisions_whenRemoteHas_thenReturnIt(code) {
            testService.getSubdivisions(code)
        }
    }

    private fun subdivisions_whenRemoteHas_thenReturnIt(
        countryCode: String,
        method: () -> List<Subdivision>
    ) {
        val expected = listOf(Subdivision(countryCode, "name", emptyList()))
        val countriesWithSubdivision = listOf(
            CountryWithSubdivision(
                Country(countryCode, "Name", emptyList(), "flag"),
                expected
            )
        )
        every { repository.getSubdivisions(any()) } returns null
        every { client.getCountriesWithSubdivisions() } returns countriesWithSubdivision
        justRun { repository.saveCountryWithSubdivisions(any()) }
        assertThat(method()).isEqualTo(expected)
        verify { repository.getSubdivisions(countryCode) }
        verify { client.getCountriesWithSubdivisions() }
        verify { repository.saveCountryWithSubdivisions(countriesWithSubdivision) }
    }

    @Test
    fun getSubdivisionsForCurrentCountry_whenCountryNotExist_thenReturnEmptyList() {
        val code = "currentCode"
        every { settings.getCurrentCountryCode() } returns code
        subdivisions_whenCountryNotExist_thenReturnEmptyList(code) { testService.getSubdivisionsForCurrentCountry() }
        verify { settings.getCurrentCountryCode() }
    }

    @Test
    fun getSubdivisions_whenCountryNotExist_thenReturnEmptyList() {
        val code = "Code"
        subdivisions_whenCountryNotExist_thenReturnEmptyList(code) {
            testService.getSubdivisions(code)
        }
    }

    private fun subdivisions_whenCountryNotExist_thenReturnEmptyList(
        countryCode: String,
        method: () -> List<Subdivision>
    ) {
        val anotherCountryCode = "another$countryCode"
        val expected = listOf(Subdivision(anotherCountryCode, "name", emptyList()))
        val countriesWithSubdivision = listOf(
            CountryWithSubdivision(
                Country(anotherCountryCode, "Name", emptyList(), "flag"),
                expected
            )
        )
        every { repository.getSubdivisions(any()) } returns null
        every { client.getCountriesWithSubdivisions() } returns countriesWithSubdivision
        justRun { repository.saveCountryWithSubdivisions(any()) }
        assertThat(method()).isNotNull.isEmpty()
        verify { repository.getSubdivisions(countryCode) }
        verify { client.getCountriesWithSubdivisions() }
        verify { repository.saveCountryWithSubdivisions(countriesWithSubdivision) }
    }
}