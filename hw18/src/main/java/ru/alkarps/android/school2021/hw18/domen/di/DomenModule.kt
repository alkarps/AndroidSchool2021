package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Binds
import dagger.Module
import ru.alkarps.android.school2021.hw18.domen.country.CountryInteractor
import ru.alkarps.android.school2021.hw18.domen.country.impl.ImplCountryInteractor
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.holiday.impl.ImplHolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.language.impl.ImplLanguageInteractor

/**
 * Модуль биндинга бизнес-слоя
 */
@Module
interface DomenModule {
    @Binds
    fun bindsHolidayService(impl: ImplHolidayInteractor): HolidayInteractor

    @Binds
    fun bindsLanguageService(impl: ImplLanguageInteractor): LanguageInteractor

    @Binds
    fun bindsCountryService(impl: ImplCountryInteractor): CountryInteractor
}