package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.holiday.impl.ImplHolidayService

/**
 * Модуль биндинга бизнес-слоя
 */
@Module
interface DomenModule {
    @Binds
    fun bindsHolidayService(holidayService: ImplHolidayService): HolidayService
}