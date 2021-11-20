package ru.alkarps.android.school2021.hw18.domen.holiday.impl

import ru.alkarps.android.school2021.hw18.domen.country.CountryInteractor
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Holiday
import ru.alkarps.android.school2021.hw18.domen.model.Period
import javax.inject.Inject

/**
 * Реализация сервиса [HolidayInteractor]
 *
 * @property client клиент для получения праздников
 * @property language сервис для работы с доступными языками
 * @property country сервис для работы с доступными странами и ТП
 * @constructor Создает новую реализацию [HolidayInteractor]
 */
class ImplHolidayInteractor @Inject constructor(
    private val client: HolidayClient,
    private val language: LanguageInteractor,
    private val country: CountryInteractor
) : HolidayInteractor {
    override fun getHolidays(period: Period): List<Holiday> {
        return client.getHoliday(
            period,
            language.getCurrentLanguage().code,
            country.getCurrentSubdivision().code
        )
    }
}