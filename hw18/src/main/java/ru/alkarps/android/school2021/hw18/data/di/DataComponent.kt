package ru.alkarps.android.school2021.hw18.data.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.data.holiday.ImplHolidayClient

@Component(modules = [DataExternalDependenciesModule::class, DataBindsModule::class])
interface DataComponent {
    fun implHolidayClient(): ImplHolidayClient
}