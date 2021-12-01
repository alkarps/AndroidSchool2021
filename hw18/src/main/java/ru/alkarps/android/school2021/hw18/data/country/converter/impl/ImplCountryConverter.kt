package ru.alkarps.android.school2021.hw18.data.country.converter.impl

import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.data.country.model.SubdivisionDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryWithSubdivisionsEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import javax.inject.Inject

/**
 * Реализация [CountryConverter]
 */
class ImplCountryConverter @Inject constructor() : CountryConverter {
    override fun fromDto(countries: List<CountryDTO>): List<Country> =
        countries.map { toCountry(it) }

    private fun toCountry(country: CountryDTO): Country =
        Country(
            country.code.lowercase(),
            country.name,
            country.languages.map { it.lowercase() },
            country.flag,
            country.subdivisions.map { toSubdivision(it) }
                .toMutableList()
                .apply { add(toSubdivision(country)) }
        )

    private fun toSubdivision(subdivision: SubdivisionDTO): Subdivision =
        Subdivision(
            subdivision.code.lowercase(),
            subdivision.name,
            subdivision.languages.map { it.lowercase() })

    private fun toSubdivision(country: CountryDTO): Subdivision =
        Subdivision(
            country.code.lowercase(),
            country.name,
            country.languages.map { it.lowercase() })

    override fun fromEntity(country: CountryWithSubdivisionsEntity): Country =
        Country(
            country.country.code,
            country.country.name,
            country.country.languages.split(","),
            country.country.flag,
            country.subdivisions.map { fromEntity(it) }
        )

    override fun fromEntity(subdivision: SubdivisionEntity): Subdivision =
        Subdivision(subdivision.code, subdivision.name, subdivision.languages.split(","))

    override fun toEntity(country: Country): CountryWithSubdivisionsEntity =
        CountryWithSubdivisionsEntity(
            toCountyEntity(country),
            country.subdivisions.map { toSubdivisionEntity(it, country.code) }
        )

    private fun toCountyEntity(country: Country): CountryEntity =
        CountryEntity(
            country.code.lowercase(),
            country.name,
            country.languageCodes.joinToString(","),
            country.flag
        )

    private fun toSubdivisionEntity(subdivision: Subdivision, countryCode: String) =
        SubdivisionEntity(
            subdivision.code.lowercase(),
            countryCode.lowercase(),
            subdivision.name,
            subdivision.languageCodes.joinToString(",")
        )
}