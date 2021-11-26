package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventCreateViewModel
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.di.holiday.HolidayMainScope
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import javax.inject.Inject

/**
 * Фабрика [EventCreateViewModel]
 *
 * @constructor Новый инстанс [ViewModelProvider.Factory]
 */
@HolidayMainScope
class EventCreateViewModelFactory @Inject constructor(
    private val schedulersProvider: SchedulersProvider,
    private val eventsController: EventsController
) : ViewModelProvider.Factory {
    /**
     * Метод создания [EventCreateViewModel]
     *
     * @param T [ViewModel]
     * @param modelClass класс [ViewModel]
     * @return экземпляр [MainViewModel]
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventCreateViewModel(schedulersProvider, eventsController) as T
    }
}