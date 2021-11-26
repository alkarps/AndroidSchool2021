package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventCreateOrUpdateViewModel
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.di.holiday.HolidayMainScope
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import javax.inject.Inject

/**
 * Фабрика [EventCreateOrUpdateViewModel]
 *
 * @constructor Новый инстанс [ViewModelProvider.Factory]
 */
@HolidayMainScope
class EventCreateOrUpdateViewModelFactory @Inject constructor(
    private val schedulersProvider: SchedulersProvider,
    private val eventsController: EventsController
) : ViewModelProvider.Factory {
    /**
     * Метод создания [EventCreateOrUpdateViewModel]
     *
     * @param T [ViewModel]
     * @param modelClass класс [ViewModel]
     * @return экземпляр [MainViewModel]
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventCreateOrUpdateViewModel(schedulersProvider, eventsController) as T
    }
}