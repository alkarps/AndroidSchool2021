package ru.alkarps.android.school2021.hw18.presentation.di.holiday

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.presentation.provider.*
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.EventConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl.ImplCountryConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl.ImplEventConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl.ImplHolidayConverter
import ru.alkarps.android.school2021.hw18.presentation.provider.impl.*

/**
 * Модуль Presentation слоя для биндинга реализаций к интерфейсам
 */
@Module
interface HolidayMainModule {
    @Binds
    fun holidayConverter(impl: ImplHolidayConverter): HolidayConverter

    @Binds
    fun countryConverter(impl: ImplCountryConverter): CountryConverter

    @Binds
    fun eventConverter(impl: ImplEventConverter): EventConverter

    @Binds
    fun bindsSchedulersProvider(impl: ImplSchedulersProvider): SchedulersProvider

    @Binds
    fun bindsHolidaysProvider(impl: ImplHolidaysProvider): HolidaysProvider

    @Binds
    fun bindsLanguagesProvider(impl: ImplLanguagesProvider): LanguagesProvider

    @Binds
    fun bindsCountriesProvider(impl: ImplCountriesProvider): CountriesProvider

    @Binds
    fun bindsEventsController(impl: ImplEventsController): EventsController
}