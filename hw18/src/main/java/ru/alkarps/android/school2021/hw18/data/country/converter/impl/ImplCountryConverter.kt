package ru.alkarps.android.school2021.hw18.data.country.converter.impl

import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.data.country.model.SubdivisionDTO
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import javax.inject.Inject

/**
 * Реализация [CountryConverter]
 */
class ImplCountryConverter @Inject constructor() : CountryConverter {
    override fun fromDto(countries: List<CountryDTO>): List<CountryWithSubdivision> =
        countries.map {
            CountryWithSubdivision(
                toCountry(it),
                it.subdivisions.map(this::toSubdivision)
                    .toMutableList()
                    .apply { add(toSubdivision(it)) }
            )
        }

    private fun toCountry(country: CountryDTO): Country =
        Country(
            country.code.lowercase(),
            country.name,
            country.languages.map { it.lowercase() },
            country.flag
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
}