package ru.alkarps.android.school2021.hw18.domen.holiday.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

class ImplHolidayServiceTest {
    private lateinit var client: HolidayClient
    private lateinit var language: LanguageService
    private lateinit var country: CountryService
    private lateinit var testService: HolidayService

    @Before
    fun setUp() {
        client = mockk()
        language = mockk()
        country = mockk()
        testService = ImplHolidayService(client, language, country)
    }

    @Test
    fun getHolidays_whenCall_thenReturnHoliday() {
        val languageCode = "LanguageCode"
        val subdivisionCode = "SubdivisionCode"
        val period = Period(2020)
        val expected = listOf(Holiday("", "", "", "", true))
        every { language.getCurrentLanguage() } returns Language(languageCode, "Name")
        every { country.getCurrentSubdivision() } returns Subdivision(
            subdivisionCode,
            "Name",
            emptyList()
        )
        every { client.getHoliday(any(), any(), any()) } returns expected
        assertThat(testService.getHolidays(period)).isEqualTo(expected)
        verify { language.getCurrentLanguage() }
        verify { country.getCurrentSubdivision() }
        verify { client.getHoliday(period, languageCode, subdivisionCode) }
    }
}