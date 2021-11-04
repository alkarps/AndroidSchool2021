package ru.alkarps.android.school2021.hw18.data.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.data.holiday.ImplHolidayClient
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.api.impl.ImplHolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.data.holiday.converter.impl.ImplHolidayConverter
import ru.alkarps.android.school2021.hw18.data.language.ImplLanguageClient
import ru.alkarps.android.school2021.hw18.data.language.api.LanguageApi
import ru.alkarps.android.school2021.hw18.data.language.api.impl.ImplLanguageApi
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.converter.impl.ImplLanguageConverter
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient

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

    @Binds
    fun languageApi(impl: ImplLanguageApi): LanguageApi

    @Binds
    fun languageConverter(impl: ImplLanguageConverter): LanguageConverter

    @Binds
    fun bindsLanguageClient(impl: ImplLanguageClient): LanguageClient
}