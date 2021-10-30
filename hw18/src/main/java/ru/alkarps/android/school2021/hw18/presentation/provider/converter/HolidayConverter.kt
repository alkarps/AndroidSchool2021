package ru.alkarps.android.school2021.hw18.presentation.provider.converter

import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

/**
 * Конвертер [Holiday] в модель данных UI
 */
interface HolidayConverter {
    /**
     * Метод конвертации
     *
     * @param holidays праздники
     * @return праздники в разрезе дней для отображения на UI
     */
    fun toView(holidays: List<Holiday>): List<DayWithHolidaysView>
}