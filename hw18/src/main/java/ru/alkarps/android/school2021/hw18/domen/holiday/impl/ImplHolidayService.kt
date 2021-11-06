package ru.alkarps.android.school2021.hw18.domen.holiday.impl

import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period
import javax.inject.Inject

/**
 * Реализация сервиса [HolidayService]
 *
 * @property client клиент для получения праздников
 * @constructor Создает новую реализацию [HolidayService]
 */
class ImplHolidayService @Inject constructor(
    private val client: HolidayClient,
    private val language: LanguageService
) : HolidayService {
    override fun getHolidays(period: Period): List<Holiday> {
        return client.getHoliday(period, language.getCurrentLanguage().code, "RU")
    }
}