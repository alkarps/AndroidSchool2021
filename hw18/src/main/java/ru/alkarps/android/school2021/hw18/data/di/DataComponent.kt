package ru.alkarps.android.school2021.hw18.data.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayClient

@Component(modules = [DataExternalDependenciesModule::class, DataBindsModule::class])
interface DataComponent {
    fun holidayClient(): HolidayClient
}