package ru.alkarps.android.school2021.hw18.view.provider

import io.reactivex.Single
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.view.model.DayWithHolidaysView

/**
 * Провайдер праздников
 */
interface HolidaysProvider {
    /**
     * Метод получения праздников в разрезе по дням за заданный период
     *
     * @param period период, за который необходимо получить праздники
     * @return Праздники в разрезе по дням
     */
    fun getHolidaysByPeriod(period: Period): Single<List<DayWithHolidaysView>>
}