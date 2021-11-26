package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

/**
 * Event create view model
 *
 * @property schedulersProvider
 * @property eventsController
 * @constructor Create empty Event create view model
 */
class EventCreateOrUpdateViewModel(
    private val schedulersProvider: SchedulersProvider,
    private val eventsController: EventsController
) : ViewModel() {
    private var disposable: Disposable? = null
    val success = MutableLiveData<Boolean>()
    val errorMessages = MutableLiveData<String>()
    fun createOrUpdate(event: EventView) {
        disposable = eventsController.createOrUpdate(event)
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe({ success.value = true }, this::handleThrowable)
    }

    private fun handleThrowable(t: Throwable) {
        Log.i(TAG, t.message, t)
        errorMessages.value = ERROR_MESSAGE
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.let { if (!it.isDisposed) it.isDisposed }
        disposable = null
    }

    companion object {
        private const val TAG = "EventCreateOrUpdateViewModel"
        private const val ERROR_MESSAGE =
            "При получении создании события произошла ошибка. Пожалуйста, повторите попытку позже"
    }
}