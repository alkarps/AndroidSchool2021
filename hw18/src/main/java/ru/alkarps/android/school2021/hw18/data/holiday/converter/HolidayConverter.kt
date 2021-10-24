package ru.alkarps.android.school2021.hw18.data.holiday.converter

import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.domen.model.Holiday

/**
 * Конвертер список [HolidayDTO] в список [Holiday]
 */
interface HolidayConverter {
    /**
     * Метод конвертации
     *
     * @param holidays список полученных праздников
     * @return список праздников
     */
    fun fromDto(holidays: List<HolidayDTO>): List<Holiday>
}