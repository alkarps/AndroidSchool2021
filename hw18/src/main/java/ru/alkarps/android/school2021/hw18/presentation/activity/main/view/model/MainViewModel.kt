package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import ru.alkarps.android.school2021.hw18.util.asString
import ru.alkarps.android.school2021.hw18.util.toPeriod
import java.util.*

/**
 * [ViewModel] для главного экрана приложения
 *
 * @property schedulersProvider провайдер шедулеров
 * @property holidaysProvider провайдер праздников
 * @constructor Новый инстанс [MainViewModel]
 */
class MainViewModel constructor(
    private val schedulersProvider: SchedulersProvider,
    private val holidaysProvider: HolidaysProvider,
    private val eventsController: EventsController
) : ViewModel() {
    private var holidayDisposable: Disposable? = null
    private var eventsDisposable: Disposable? = null
    private val holidayProgress = MutableLiveData<Boolean>()
    private val eventsProgress = MutableLiveData<Boolean>()
    val holidays = MutableLiveData<List<DayWithHolidaysView>>()
    val events = MutableLiveData<List<EventView>>()
    val errorMessages = MutableLiveData<String>()
    val progress = MediatorLiveData<Boolean>().apply {
        val doCalculation = Observer<Boolean> {
            value = holidayProgress.value == true && eventsProgress.value == true
        }
        addSource(holidayProgress, doCalculation)
        addSource(eventsProgress, doCalculation)
    }

    /**
     * Загрузка праздников за указанный день
     */
    fun loadDataByDate(date: Calendar) {
        loadHolidays(date.toPeriod())
        loadEvents(date.asString())
    }

    private fun loadHolidays(period: Period) {
        holidayDisposable = holidaysProvider.getHolidaysByPeriod(period)
            .doOnSubscribe { holidayProgress.postValue(false) }
            .doAfterTerminate { holidayProgress.postValue(true) }
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe(holidays::setValue, this::handleHolidayError)
    }

    private fun handleHolidayError(t: Throwable) {
        holidays.value = emptyList()
        logError(t)
        errorMessages.value = HOLIDAY_ERROR_MESSAGE
    }

    private fun logError(t: Throwable) {
        Log.i(TAG, t.message, t)
    }

    private fun loadEvents(date: String) {
        holidayDisposable = eventsController.getByDate(date)
            .doOnSubscribe { eventsProgress.postValue(false) }
            .doAfterTerminate { eventsProgress.postValue(true) }
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe(events::setValue, this::handleEventsError)
    }

    private fun handleEventsError(t: Throwable) {
        events.value = emptyList()
        logError(t)
        errorMessages.value = EVENT_ERROR_MESSAGE
    }

    override fun onCleared() {
        super.onCleared()
        holidayDisposable?.let { if (!it.isDisposed) it.isDisposed }
        holidayDisposable = null
        eventsDisposable?.let { if (!it.isDisposed) it.isDisposed }
        eventsDisposable = null
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val HOLIDAY_ERROR_MESSAGE =
            "При получении праздников произошла ошибка. Пожалуйста, повторите попытку позже"
        private const val EVENT_ERROR_MESSAGE =
            "При получении событий произошла ошибка. Пожалуйста, повторите попытку позже"
    }
}