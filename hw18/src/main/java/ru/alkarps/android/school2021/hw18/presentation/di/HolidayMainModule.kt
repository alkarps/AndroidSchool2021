package ru.alkarps.android.school2021.hw18.presentation.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl.ImplHolidayConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.impl.ImplHolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.impl.ImplSchedulersProvider

/**
 * Модуль Presentation слоя для биндинга реализаций к интерфейсам
 */
@Module
interface HolidayMainModule {
    @Binds
    fun holidayConverter(impl: ImplHolidayConverter): HolidayConverter

    @Binds
    fun bindsSchedulersProvider(impl: ImplSchedulersProvider): SchedulersProvider

    @Binds
    fun bindsHolidaysProvider(impl: ImplHolidaysProvider): HolidaysProvider
}