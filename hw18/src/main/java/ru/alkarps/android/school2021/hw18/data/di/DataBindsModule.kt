package ru.alkarps.android.school2021.hw18.data.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.data.country.ImplCountryClient
import ru.alkarps.android.school2021.hw18.data.country.ImplCountryRepository
import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.api.impl.ImplCountryApi
import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.country.converter.impl.ImplCountryConverter
import ru.alkarps.android.school2021.hw18.data.event.ImplEventRepository
import ru.alkarps.android.school2021.hw18.data.event.converter.EventConverter
import ru.alkarps.android.school2021.hw18.data.event.converter.impl.ImplEventConverter
import ru.alkarps.android.school2021.hw18.data.holiday.ImplHolidayClient
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.api.impl.ImplHolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.converter.HolidayConverter
import ru.alkarps.android.school2021.hw18.data.holiday.converter.impl.ImplHolidayConverter
import ru.alkarps.android.school2021.hw18.data.language.ImplLanguageClient
import ru.alkarps.android.school2021.hw18.data.language.ImplLanguageRepository
import ru.alkarps.android.school2021.hw18.data.language.api.LanguageApi
import ru.alkarps.android.school2021.hw18.data.language.api.impl.ImplLanguageApi
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.converter.impl.ImplLanguageConverter
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository
import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

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
    fun bindsSettingsRepository(impl: ImplSettingsRepository): SettingsRepository

    @Binds
    fun languageApi(impl: ImplLanguageApi): LanguageApi

    @Binds
    fun languageConverter(impl: ImplLanguageConverter): LanguageConverter

    @Binds
    fun bindsLanguageClient(impl: ImplLanguageClient): LanguageClient

    @Binds
    fun bindsLanguageRepository(impl: ImplLanguageRepository): LanguageRepository

    @Binds
    fun countryApi(impl: ImplCountryApi): CountryApi

    @Binds
    fun countryConverter(impl: ImplCountryConverter): CountryConverter

    @Binds
    fun bindsCountryClient(impl: ImplCountryClient): CountryClient

    @Binds
    fun bindsCountryRepository(impl: ImplCountryRepository): CountryRepository

    @Binds
    fun bindsEventRepository(impl: ImplEventRepository): EventRepository

    @Binds
    fun bindsEventConverter(impl: ImplEventConverter): EventConverter
}