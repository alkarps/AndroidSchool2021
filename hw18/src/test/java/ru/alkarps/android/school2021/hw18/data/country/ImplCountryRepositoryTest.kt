package ru.alkarps.android.school2021.hw18.data.country

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.CountryDao
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryWithSubdivisionsEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

class ImplCountryRepositoryTest {
    private lateinit var dao: CountryDao
    private lateinit var converter: CountryConverter
    private lateinit var repository: CountryRepository

    @Before
    fun setUp() {
        dao = mockk()
        converter = mockk()
        repository = ImplCountryRepository(dao, converter)
    }

    @Test
    fun getCountries_whenFindCountriesIsNotEmpty_thenReturnIt() {
        val country = getCountryEntity()
        val expected = getCountry()

        every { dao.getCountries() } returns listOf(country)
        every { converter.fromEntity(any<CountryWithSubdivisionsEntity>()) } returns expected

        assertThat(repository.getCountries()).isNotNull.isEqualTo(listOf(expected))

        verifySequence {
            dao.getCountries()
            converter.fromEntity(country)
        }

        confirmVerified(dao, converter)
    }

    @Test
    fun getCountries_whenFindCountriesIsEmpty_thenReturnNull() {
        every { dao.getCountries() } returns emptyList()

        assertThat(repository.getCountries()).isNull()

        verifySequence {
            dao.getCountries()
        }

        confirmVerified(dao, converter)
    }

    @Test
    fun findSubdivision_whenFound_thenReturnIt() {
        val code = "SubCode"
        val subdivision = getSubdivisionEntity()
        val expected = getSubdivision()

        every { dao.findSubdivision(any()) } returns subdivision
        every { converter.fromEntity(any<SubdivisionEntity>()) } returns expected

        assertThat(repository.findSubdivision(code)).isEqualTo(expected)

        verifySequence {
            dao.findSubdivision(code.lowercase())
            converter.fromEntity(subdivision)
        }

        confirmVerified(dao, converter)
    }

    @Test
    fun findSubdivision_whenNotFound_thenReturnNull() {
        val code = "SubCode"

        every { dao.findSubdivision(any()) } returns null

        assertThat(repository.findSubdivision(code)).isNull()

        verifySequence {
            dao.findSubdivision(code.lowercase())
        }

        confirmVerified(dao, converter)
    }

    @Test
    fun saveCountries() {
        val country = getCountry()
        val entity = getCountryEntity()

        justRun { dao.insert(any()) }
        every { converter.toEntity(any()) } returns entity

        assertThatCode { repository.saveCountries(listOf(country)) }.doesNotThrowAnyException()

        verifySequence {
            converter.toEntity(country)
            dao.insert(listOf(entity))
        }
    }

    private fun getCountry() =
        Country("Code", "Name", listOf("Lang"), "Flag", listOf(getSubdivision()))

    private fun getSubdivision() = Subdivision("SubCode", "SubName", listOf("SubLang"))

    private fun getCountryEntity() = CountryWithSubdivisionsEntity(
        CountryEntity("Code", "Name", "Lang", "Flag"),
        listOf(getSubdivisionEntity())
    )

    private fun getSubdivisionEntity() =
        SubdivisionEntity("SubCode", "Code", "SubName", "SubLang")
}