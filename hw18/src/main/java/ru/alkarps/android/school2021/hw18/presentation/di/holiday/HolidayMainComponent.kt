package ru.alkarps.android.school2021.hw18.presentation.di.holiday

import dagger.Subcomponent
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory.EventChangeViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory.EventCreateViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.factory.SettingsViewModelFactory
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController

/**
 * Компонент Presentation слоя
 */
@HolidayMainScope
@Subcomponent(modules = [HolidayMainModule::class])
interface HolidayMainComponent {
    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel]
     *
     * @return экземпляр [MainViewModelFactory]
     */
    fun mainViewModelFactory(): MainViewModelFactory

    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel]
     *
     * @return экземпляр [SettingsViewModelFactory]
     */
    fun settingsViewModelFactory(): SettingsViewModelFactory

    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventCreateViewModel]
     *
     * @return экземпляр [EventCreateViewModelFactory]
     */
    fun eventCreateViewModelFactory(): EventCreateViewModelFactory

    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventChangeViewModel]
     *
     * @return экземпляр [EventCreateViewModelFactory]
     */
    fun eventChangeViewModelFactory(): EventChangeViewModelFactory

    /**
     * Метод получения эземляра [EventsController]
     *
     * @return экземпляр [EventsController]
     */
    fun eventsController(): EventsController

    @Subcomponent.Factory
    interface Factory {
        fun create(): HolidayMainComponent
    }
}