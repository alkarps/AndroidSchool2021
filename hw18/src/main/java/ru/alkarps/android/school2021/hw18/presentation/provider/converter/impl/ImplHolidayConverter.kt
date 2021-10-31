package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter
import javax.inject.Inject

/**
 * Реализация [HolidayConverter]
 *
 * @constructor Новый объект реализации [HolidayConverter]
 */
class ImplHolidayConverter @Inject constructor() : HolidayConverter {
    override fun toView(holidays: List<Holiday>): List<DayWithHolidaysView> = holidays
        .groupBy { it.date }.map { DayWithHolidaysView(it.key, it.value.map(this::toHolidayView)) }

    private fun toHolidayView(holiday: Holiday): HolidayView =
        HolidayView(holiday.uuid, holiday.name, holiday.date, holiday.observed, holiday.public)
}