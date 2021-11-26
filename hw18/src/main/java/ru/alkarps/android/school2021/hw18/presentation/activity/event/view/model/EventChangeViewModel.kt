package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

/**
 * [ViewModel] для изменения и удаления событий
 *
 * @property schedulersProvider провайдер для управления потоками
 * @property eventsController контроллер для работы с событиями
 * @constructor Create empty Event create view model
 */
class EventChangeViewModel(
    private val schedulersProvider: SchedulersProvider,
    private val eventsController: EventsController
) : ViewModel() {
    private var deleteDisposable: Disposable? = null
    private var updateDisposable: Disposable? = null
    val success = MutableLiveData<Boolean>()
    val errorMessages = MutableLiveData<String>()

    /**
     * Метод изменения события
     *
     * @param event описание изменяемого события
     */
    fun update(event: EventView) {
        updateDisposable = subscribe { eventsController.createOrUpdate(event) }
    }

    /**
     * Метод удаления события
     *
     * @param event описание удаляемого события
     */
    fun delete(event: EventView) {
        updateDisposable = subscribe { eventsController.delete(event) }
    }

    private fun subscribe(run: () -> Completable) = run()
        .subscribeOn(schedulersProvider.back())
        .observeOn(schedulersProvider.main())
        .subscribe({ success.value = true }, this::handleThrowable)

    private fun handleThrowable(t: Throwable) {
        Log.i(TAG, t.message, t)
        errorMessages.value = ERROR_MESSAGE
    }

    override fun onCleared() {
        super.onCleared()
        updateDisposable?.let { if (!it.isDisposed) it.isDisposed }
        updateDisposable = null
        deleteDisposable?.let { if (!it.isDisposed) it.isDisposed }
        deleteDisposable = null
    }

    companion object {
        private const val TAG = "EventCreateViewModel"
        private const val ERROR_MESSAGE =
            "При получении создании события произошла ошибка. Пожалуйста, повторите попытку позже"
    }
}