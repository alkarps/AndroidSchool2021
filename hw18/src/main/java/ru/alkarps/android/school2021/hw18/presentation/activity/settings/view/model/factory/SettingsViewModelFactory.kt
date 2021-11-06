package ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel
import ru.alkarps.android.school2021.hw18.presentation.di.HolidayMainScope
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.LanguagesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import javax.inject.Inject

/**
 * Фабрика [SettingsViewModel]
 *
 * @constructor Новый инстанс [ViewModelProvider.Factory]
 */
@HolidayMainScope
class SettingsViewModelFactory @Inject constructor(
    private val schedulersProvider: SchedulersProvider,
    private val languagesProvider: LanguagesProvider,
    private val countriesProvider: CountriesProvider
) : ViewModelProvider.Factory {
    /**
     * Метод создания [SettingsViewModel]
     *
     * @param T [ViewModel]
     * @param modelClass класс [ViewModel]
     * @return экземпляр [SettingsViewModel]
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(schedulersProvider, languagesProvider, countriesProvider) as T
    }
}