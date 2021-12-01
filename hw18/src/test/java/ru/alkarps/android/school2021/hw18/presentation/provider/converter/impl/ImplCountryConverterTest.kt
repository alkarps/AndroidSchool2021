package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.DivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter

class ImplCountryConverterTest {
    private val code = "Code"
    private val name = "Name"
    private lateinit var testConverter: CountryConverter

    @Before
    fun setUp() {
        testConverter = ImplCountryConverter()
    }

    @Test
    fun countriesToView() {
        val country = Country(
            code, name, emptyList(), "flag", listOf(Subdivision(code, name, emptyList()))
        )
        assertThat(testConverter.countriesToView(listOf(country))).isEqualTo(
            listOf(
                CountryView(DivisionView(code, name), listOf(DivisionView(code, name)))
            )
        )
    }
}