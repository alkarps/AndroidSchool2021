package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter

class ImplHolidaysProviderTest {

    @Test
    fun getHolidaysByPeriod() {
        val holidays = listOf(Holiday("", "", "day", "", true))
        val expected =
            listOf(DayWithHolidaysView("day", listOf(HolidayView("", "", "day", "", true))))
        val period = Period(2020)

        val service = mockk<HolidayInteractor>()
        every { service.getHolidays(any()) } returns holidays
        val converter = mockk<HolidayConverter>()
        every { converter.toView(any()) } returns expected

        val holidaysSingle = ImplHolidaysProvider(service, converter)
            .getHolidaysByPeriod(period)
            .test()

        holidaysSingle.assertValue(expected)

        verify { service.getHolidays(period) }
        verify { converter.toView(holidays) }
    }
}