package ru.alkarps.android.school2021.hw18.data.holiday

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.data.holiday.model.DateInfoDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.WeekdayDTO
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period

class ImplHolidayClientTest {
    private lateinit var api: HolidayApi
    private lateinit var converter: HolidayConverter
    private lateinit var testClient: HolidayClient

    @Before
    fun setUp() {
        api = mockk()
        converter = mockk()
        testClient = ImplHolidayClient(api, converter)
    }

    @Test
    fun getHoliday() {
        val period = Period(2020)
        val language = "RU"
        val country = "RU"

        val holidaysDto = listOf(
            HolidayDTO(
                "", "", "", false, "", "",
                WeekdayDTO(DateInfoDTO("", ""), DateInfoDTO("", ""))
            )
        )
        val holidays = listOf(Holiday("", "", "", "", false))

        every { api.getHolidays(any(), any(), any()) } returns holidaysDto
        every { converter.fromDto(any()) } returns holidays

        assertThat(testClient.getHoliday(period, language, country)).isEqualTo(holidays)

        verify { api.getHolidays(period, country, language) }
        verify { converter.fromDto(holidaysDto) }
    }
}