package ru.alkarps.android.school2021.hw18.data.country.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.data.country.model.SubdivisionDTO
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

class ImplCountryConverterTest {

    @Test
    fun fromDto() {
        val subdivisionDTO = SubdivisionDTO("subCode", "subName", listOf("subLang"))
        val countryDTO =
            CountryDTO("Code", "Name", emptyMap(), listOf("Lang"), "Flag", listOf(subdivisionDTO))
        assertThat(ImplCountryConverter().fromDto(listOf(countryDTO)))
            .isNotNull
            .hasSize(1)
            .isEqualTo(
                listOf(
                    CountryWithSubdivision(
                        Country(
                            countryDTO.code,
                            countryDTO.name,
                            countryDTO.languages,
                            countryDTO.flag
                        ),
                        listOf(
                            Subdivision(
                                subdivisionDTO.code,
                                subdivisionDTO.name,
                                subdivisionDTO.languages
                            )
                        )
                    )
                )
            )
    }
}