package ru.alkarps.android.school2021.hw18.data.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient

/**
 * Компонент Data слоя
 */
@DataScope
@Component(modules = [DataExternalDependenciesModule::class, DataBindsModule::class])
interface DataComponent {
    /**
     * Клиент для получения праздников
     *
     * @return инстанс реализации [HolidayClient]
     */
    fun holidayClient(): HolidayClient

    /**
     * Клиент для получения доступных языков
     *
     * @return инстанс реализации [LanguageClient]
     */
    fun languageClient(): LanguageClient
}