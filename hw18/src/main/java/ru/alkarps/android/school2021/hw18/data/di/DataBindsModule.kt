package ru.alkarps.android.school2021.hw18.data.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.data.holiday.ImplHolidayClient
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.api.impl.ImplHolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.data.holiday.converter.impl.ImplHolidayConverter
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient

/**
 * Модуль биндинга Data-слоя
 */
@Module
interface DataBindsModule {
    @Binds
    fun holidayApi(impl: ImplHolidayApi): HolidayApi

    @Binds
    fun holidayConverter(impl: ImplHolidayConverter): HolidayConverter

    @Binds
    fun bindsHolidayClient(impl: ImplHolidayClient): HolidayClient
}