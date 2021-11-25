package ru.alkarps.android.school2021.hw18.data.country.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.data.country.model.SubdivisionDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryWithSubdivisionsEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

class ImplCountryConverterTest {
    private val countryCode = "Code"
    private val countryName = "Name"
    private val countryLang = listOf("Lang1", "Lang2")
    private val countryLangStr = "Lang1,Lang2"
    private val countryFlag = "Flag"
    private val subdivisionCode = "subCode"
    private val subdivisionName = "subName"
    private val subdivisionLang = listOf("subLang1", "subLang2")
    private val subdivisionLangStr = "subLang1,subLang2"

    @Test
    fun fromDto() {
        val subdivisionDTO = SubdivisionDTO(subdivisionCode, subdivisionName, subdivisionLang)
        val countryDTO = CountryDTO(
            countryCode, countryName, emptyMap(), countryLang, countryFlag,
            listOf(subdivisionDTO)
        )

        val expected = listOf(
            Country(
                countryCode.lowercase(), countryName,
                countryLang.map { it.lowercase() }, countryFlag,
                listOf(
                    Subdivision(
                        subdivisionCode.lowercase(), subdivisionName,
                        subdivisionLang.map { it.lowercase() }
                    ),
                    Subdivision(
                        countryCode.lowercase(), countryName,
                        countryLang.map { it.lowercase() }
                    )
                )
            )
        )

        assertThat(ImplCountryConverter().fromDto(listOf(countryDTO)))
            .isNotNull
            .hasSize(1)
            .isEqualTo(expected)
    }

    @Test
    fun fromEntity_whenSubdivisionEntity_thenConvertToSubdivision() {
        val subdivision =
            SubdivisionEntity(subdivisionCode, countryCode, subdivisionName, subdivisionLangStr)
        val expected = Subdivision(subdivisionCode, subdivisionName, subdivisionLang)

        assertThat(ImplCountryConverter().fromEntity(subdivision))
            .isNotNull
            .isEqualTo(expected)
    }

    @Test
    fun fromEntity_whenCountryWithSubdivision_whenConvertToCountry() {
        val country = CountryWithSubdivisionsEntity(
            CountryEntity(countryCode, countryName, countryLangStr, countryFlag),
            listOf(
                SubdivisionEntity(
                    subdivisionCode,
                    countryCode,
                    subdivisionName,
                    subdivisionLangStr
                )
            )
        )

        val expected = Country(
            countryCode, countryName, countryLang, countryFlag,
            listOf(Subdivision(subdivisionCode, subdivisionName, subdivisionLang))
        )

        assertThat(ImplCountryConverter().fromEntity(country))
            .isNotNull
            .isEqualTo(expected)
    }

    @Test
    fun toEntity() {
        val country = Country(
            countryCode, countryName, countryLang, countryFlag,
            listOf(Subdivision(subdivisionCode, subdivisionName, subdivisionLang))
        )

        val expected = CountryWithSubdivisionsEntity(
            CountryEntity(countryCode.lowercase(), countryName, countryLangStr, countryFlag),
            listOf(
                SubdivisionEntity(
                    subdivisionCode.lowercase(), countryCode.lowercase(),
                    subdivisionName, subdivisionLangStr
                )
            )
        )

        assertThat(ImplCountryConverter().toEntity(country))
            .isNotNull
            .isEqualTo(expected)
    }
}