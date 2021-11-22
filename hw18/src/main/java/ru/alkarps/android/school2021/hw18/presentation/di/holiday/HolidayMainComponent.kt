package ru.alkarps.android.school2021.hw18.presentation.di.holiday

import dagger.Component
import dagger.Subcomponent
import ru.alkarps.android.school2021.hw18.domen.di.DomenComponent
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.factory.SettingsViewModelFactory

/**
 * Компонент Presentation слоя
 */
@HolidayMainScope
@Subcomponent(modules = [HolidayMainModule::class])
interface HolidayMainComponent {
    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel]
     *
     * @return
     */
    fun mainViewModelFactory(): MainViewModelFactory

    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel]
     *
     * @return
     */
    fun settingsViewModelFactory(): SettingsViewModelFactory

    @Subcomponent.Factory
    interface Factory{
        fun create():HolidayMainComponent
    }
}