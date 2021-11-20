package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter
import javax.inject.Inject

/**
 * Реализация [HolidaysProvider]
 *
 * @constructor Новый объект реализации [HolidaysProvider]
 */
class ImplHolidaysProvider @Inject constructor(
    private val interactor: HolidayInteractor,
    private val converter: HolidayConverter
) : HolidaysProvider {
    override fun getHolidaysByPeriod(period: Period): Single<List<DayWithHolidaysView>> =
        Single.fromCallable { interactor.getHolidays(period) }.map { converter.toView(it) }
}