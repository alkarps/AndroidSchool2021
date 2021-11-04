package ru.alkarps.android.school2021.hw18.data.holiday.converter

import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.domen.model.Holiday

/**
 * Конвертер представления праздников в дата слое в представление праздников бизнес слоя
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