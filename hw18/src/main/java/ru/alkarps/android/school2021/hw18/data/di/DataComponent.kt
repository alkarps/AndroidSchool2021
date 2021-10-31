package ru.alkarps.android.school2021.hw18.data.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient

/**
 * Компонент Data слоя
 */
@Component(modules = [DataExternalDependenciesModule::class, DataBindsModule::class])
interface DataComponent {
    /**
     * Клиент для получения праздников
     *
     * @return инстанс реализации [HolidayClient]
     */
    fun holidayClient(): HolidayClient
}