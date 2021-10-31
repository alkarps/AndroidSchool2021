package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter

/**
 * Реализация [HolidaysProvider]
 *
 * @constructor Новый объект реализации [HolidaysProvider]
 */
class ImplHolidaysProvider(
    private val service: HolidayService,
    private val converter: HolidayConverter
) : HolidaysProvider {
    override fun getHolidaysByPeriod(period: Period): Single<List<DayWithHolidaysView>> =
        Single.fromCallable { service.getHolidays(period) }.map { converter.toView(it) }
}