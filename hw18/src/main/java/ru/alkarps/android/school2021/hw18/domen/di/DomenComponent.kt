package ru.alkarps.android.school2021.hw18.domen.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.data.di.DataComponent
import ru.alkarps.android.school2021.hw18.domen.holiday.HolidayService
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService

/**
 * Компонент бизнес слоя
 */
@DomenScope
@Component(modules = [DomenModule::class], dependencies = [DataComponent::class])
interface DomenComponent {
    /**
     * Бизнес-сервис для работы с праздниками
     *
     * @return реализацию [HolidayService]
     */
    fun holidayService(): HolidayService
    /**
     * Бизнес-сервис для работы с доступными языками
     *
     * @return реализацию [LanguageService]
     */
    fun languageService(): LanguageService
}