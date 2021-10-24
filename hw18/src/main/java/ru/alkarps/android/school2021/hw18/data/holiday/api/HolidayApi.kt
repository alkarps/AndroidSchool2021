package ru.alkarps.android.school2021.hw18.data.holiday.api

import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.domen.model.Period

/**
 * API для вызове сервиса HolidayApi
 */
interface HolidayApi {
    /**
     * Получение праздников с сервера
     *
     * @param period период, за который необходимо вернуть праздники
     * @param country код страны, для которой необходимо вернуть данные
     * @param language код языка, на котором необходимо вернуть праздники
     * @return список праздников
     */
    fun getHolidays(
        period: Period,
        country: String,
        language: String
    ): List<HolidayDTO>
}