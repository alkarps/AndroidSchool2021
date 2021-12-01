package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView

class ImplHolidayConverterTest {
    private val uuid = "123"
    private val observed = "dayObserved"
    private val day1 = "day1"
    private val day2 = "day2"

    @Test
    fun toView() {
        val holiday1 = newHoliday("first", day1, true)
        val holiday2 = newHoliday("second", day1, false)
        val holiday3 = newHoliday("third", day2, false)
        assertThat(ImplHolidayConverter().toView(listOf(holiday1, holiday2, holiday3)))
            .hasSize(2)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("uuid")
            .containsOnly(
                DayWithHolidaysView(
                    day1,
                    listOf(newHolidayView(holiday1), newHolidayView(holiday2))
                ),
                DayWithHolidaysView(day2, listOf(newHolidayView(holiday3)))
            )
    }

    private fun newHoliday(name: String, date: String, public: Boolean) =
        Holiday(uuid, name, date, observed, public)

    private fun newHolidayView(holiday: Holiday) =
        HolidayView(uuid, holiday.name, holiday.date, observed, holiday.public)
}