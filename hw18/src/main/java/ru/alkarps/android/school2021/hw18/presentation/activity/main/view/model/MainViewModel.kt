package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import ru.alkarps.android.school2021.hw18.util.toPeriod
import java.util.Calendar

/**
 * [ViewModel] для главного экрана приложения
 *
 * @property schedulersProvider провайдер шедулеров
 * @property holidaysProvider провайдер праздников
 * @constructor Новый инстанс [MainViewModel]
 */
class MainViewModel constructor(
    private val schedulersProvider: SchedulersProvider,
    private val holidaysProvider: HolidaysProvider
) : ViewModel() {
    private var holidayDisposable: Disposable? = null
    private val holidayProgress = MutableLiveData<Boolean>()
    val holidays = MutableLiveData<List<DayWithHolidaysView>>()
    val errorMessages = MutableLiveData<String>()
    val progress = MediatorLiveData<Boolean>().apply {
        val doCalculation = Observer<Boolean> {
            value = holidayProgress.value == true// && countriesProgressLiveData.value == true
        }
        addSource(holidayProgress, doCalculation)
        //addSource(countriesProgressLiveData, doCalculation)
    }

    /**
     * Загрузка праздников за указанный день
     */
    fun loadHolidays(date: Calendar) {
        holidayDisposable = holidaysProvider.getHolidaysByPeriod(date.toPeriod())
            .doOnSubscribe { progress.postValue(false) }
            .doAfterTerminate { progress.postValue(true) }
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

    override fun onCleared() {
        super.onCleared()
        holidayDisposable?.let { if (!it.isDisposed) it.isDisposed }
        holidayDisposable = null
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val HOLIDAY_ERROR_MESSAGE =
            "При получении праздников произошла ошибка. Пожалуйста, повторите попытку позже"
        private const val EVENT_ERROR_MESSAGE =
            "При получении событий произошла ошибка. Пожалуйста, повторите попытку позже"
    }
}