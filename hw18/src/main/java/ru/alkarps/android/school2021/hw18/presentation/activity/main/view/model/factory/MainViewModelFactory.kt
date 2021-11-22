package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.di.holiday.HolidayMainScope
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import javax.inject.Inject

/**
 * Фабрика [MainViewModel]
 *
 * @constructor Новый инстанс [ViewModelProvider.Factory]
 */
@HolidayMainScope
class MainViewModelFactory @Inject constructor(
    private val schedulersProvider: SchedulersProvider,
    private val holidaysProvider: HolidaysProvider
) : ViewModelProvider.Factory {
    /**
     * Метод создания [MainViewModel]
     *
     * @param T [ViewModel]
     * @param modelClass класс [ViewModel]
     * @return экземпляр [MainViewModel]
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(schedulersProvider, holidaysProvider) as T
    }
}