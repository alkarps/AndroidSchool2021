package ru.alkarps.android.school2021.hw18.data.holiday.converter.impl

import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.domen.model.Holiday

class ImplHolidayConverter : HolidayConverter {
    override fun fromDto(holidays: List<HolidayDTO>): List<Holiday> = holidays.map { fromDto(it) }

    private fun fromDto(holiday: HolidayDTO) = Holiday(
        holiday.uuid,
        holiday.name,
        "${holiday.weekday.date.name} ${holiday.date}",
        "${holiday.weekday.observed.name} ${holiday.observed}",
        holiday.public
    )
}