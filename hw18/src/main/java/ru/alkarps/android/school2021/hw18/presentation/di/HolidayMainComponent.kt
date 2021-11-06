package ru.alkarps.android.school2021.hw18.presentation.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.di.DomenComponent
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.factory.SettingsViewModelFactory

/**
 * Компонент Presentation слоя
 */
@HolidayMainScope
@Component(modules = [HolidayMainModule::class], dependencies = [DomenComponent::class])
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
}