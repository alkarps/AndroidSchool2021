package ru.alkarps.android.school2021.hw18.domen.holiday.impl

import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period

/**
 * Реализация сервиса [HolidayService]
 *
 * @property client клиент для получения праздников
 * @constructor Создает новую реализацию [HolidayService]
 */
class ImplHolidayService(
    private val client: HolidayClient
) : HolidayService {
    override fun getHolidays(period: Period): List<Holiday> {
        return client.getHoliday(period, "RU", "RU")
    }
}