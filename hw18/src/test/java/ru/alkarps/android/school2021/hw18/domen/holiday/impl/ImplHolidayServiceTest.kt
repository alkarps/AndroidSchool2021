package ru.alkarps.android.school2021.hw18.domen.holiday.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period

class ImplHolidayServiceTest {
    private lateinit var client: HolidayClient
    private lateinit var testService: HolidayService

    @Before
    fun setUp() {
        client = mockk()
        testService = ImplHolidayService(client)
    }

    @Test
    fun getHolidays_whenCall_thenReturnHoliday() {
        val period = Period(2020)
        val holidays = listOf(Holiday("", "", "", "", true))
        every { client.getHoliday(any(), any(), any()) } returns holidays
        assertThat(testService.getHolidays(period)).isEqualTo(holidays)
        verify { client.getHoliday(period, "RU", "RU") }
    }
}