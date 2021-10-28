package ru.alkarps.android.school2021.hw18.view.provider.impl

import io.reactivex.Single
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.view.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.view.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.view.provider.converter.HolidayConverter

class ImplHolidaysProvider(
    private val service: HolidayService,
    private val converter: HolidayConverter
) : HolidaysProvider {
    override fun getHolidaysByPeriod(period: Period): Single<List<DayWithHolidaysView>> =
        Single.fromCallable { service.getHolidays(period) }.map { converter.toView(it) }
}