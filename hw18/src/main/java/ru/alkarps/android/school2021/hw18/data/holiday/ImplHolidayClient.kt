package ru.alkarps.android.school2021.hw18.data.holiday

import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period
import javax.inject.Inject

/**
 * Реализация [HolidayClient]
 *
 * @property api Апи для вызова сервера HolidayApi
 * @property converter Конвертер DTO во внутреннюю модель данных
 * @constructor Новый объект реализации
 */
class ImplHolidayClient @Inject constructor(
    private val api: HolidayApi,
    private val converter: HolidayConverter
) : HolidayClient {
    override fun getHoliday(period: Period, language: String, country: String): List<Holiday> {
        val holidays = api.getHolidays(period, country, language)
        return converter.fromDto(holidays)
    }
}