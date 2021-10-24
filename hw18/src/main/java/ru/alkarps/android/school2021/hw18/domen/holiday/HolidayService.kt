package ru.alkarps.android.school2021.hw18.domen.holiday

import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period

/**
 * Сервис для получения праздников по периоду
 */
interface HolidayService {
    /**
     * Метод получения [Holiday] поуказанному [Period]
     *
     * @param period Период, за который возвращать праздники
     * @return списов доступных праздников
     */
    fun getHolidays(period: Period): List<Holiday>
}