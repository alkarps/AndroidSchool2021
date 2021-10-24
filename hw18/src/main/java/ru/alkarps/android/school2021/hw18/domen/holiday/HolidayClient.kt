package ru.alkarps.android.school2021.hw18.domen.holiday

import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period

/**
 * Адаптер для клиент для получения [Holiday]
 */
interface HolidayClient {
    /**
     * Метод получения [Holiday] по параметрам
     *
     * @param period период, за который возвращать данные
     * @param language код языка
     * @param country код страны
     * @return Список доступных праздников
     */
    fun getHoliday(period: Period, language: String, country: String): List<Holiday>
}