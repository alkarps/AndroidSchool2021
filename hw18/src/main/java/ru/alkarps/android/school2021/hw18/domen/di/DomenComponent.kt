package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.data.di.DataComponent
import ru.alkarps.android.school2021.hw18.domen.country.CountryInteractor
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayInteractor
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor

/**
 * Компонент бизнес слоя
 */
@DomenScope
@Component(modules = [DomenModule::class], dependencies = [DataComponent::class])
interface DomenComponent {
    /**
     * Бизнес-сервис для работы с праздниками
     *
     * @return реализацию [HolidayInteractor]
     */
    fun holidayService(): HolidayInteractor

    /**
     * Бизнес-сервис для работы с доступными языками
     *
     * @return реализацию [LanguageInteractor]
     */
    fun languageService(): LanguageInteractor

    /**
     * Бизнес-сервис для работы с доступными странами и ТП
     *
     * @return реализацию [CountryInteractor]
     */
    fun countryService(): CountryInteractor
}