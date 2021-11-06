package ru.alkarps.android.school2021.hw18.data.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

/**
 * Компонент Data слоя
 */
@DataScope
@Component(modules = [DataExternalDependenciesModule::class, DataBindsModule::class, DataContextModule::class])
interface DataComponent {
    /**
     * Клиент для получения праздников
     *
     * @return инстанс реализации [HolidayClient]
     */
    fun holidayClient(): HolidayClient

    /**
     * Хранилище настроек приложения
     *
     * @return инстанс реализации [SettingsRepository]
     */
    fun settingsRepository(): SettingsRepository

    /**
     * Клиент для получения доступных языков
     *
     * @return инстанс реализации [LanguageClient]
     */
    fun languageClient(): LanguageClient

    /**
     * Хранилище доступных языков
     *
     * @return инстанс реализации [LanguageRepository]
     */
    fun languageRepository(): LanguageRepository

    /**
     * Клиент для получения доступных стран и ТП
     *
     * @return инстанс реализации [CountryClient]
     */
    fun countryClient(): CountryClient

    /**
     * Хранилище доступных стран и ТП
     *
     * @return инстанс реализации [CountryRepository]
     */
    fun countryRepository(): CountryRepository
}