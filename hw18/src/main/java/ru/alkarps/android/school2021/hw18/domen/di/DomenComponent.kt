package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.data.di.DataComponent
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService

@Component(modules = [DomenModule::class], dependencies = [DataComponent::class])
interface DomenComponent {
    fun holidayService(): HolidayService
}