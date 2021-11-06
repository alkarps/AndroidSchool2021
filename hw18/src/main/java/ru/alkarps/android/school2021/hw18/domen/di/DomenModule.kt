package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.holiday.impl.ImplHolidayService
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.language.impl.ImplLanguageService

/**
 * Модуль биндинга бизнес-слоя
 */
@Module
interface DomenModule {
    @Binds
    fun bindsHolidayService(impl: ImplHolidayService): HolidayService

    @Binds
    fun bindsLanguageService(impl: ImplLanguageService): LanguageService
}