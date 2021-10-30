package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

/**
 * [ViewModel] для главного экрана приложения
 *
 * @property schedulersProvider провайдер шедулеров
 * @property holidaysProvider провайдер праздников
 * @constructor Новый инстанс [MainViewModel]
 */
class MainViewModel(
    private val schedulersProvider: SchedulersProvider,
    private val holidaysProvider: HolidaysProvider
) : ViewModel() {
    private var disposable: Disposable? = null
    val progressLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()
    val holidaysLiveData = MutableLiveData<List<DayWithHolidaysView>>()

    /**
     * Загрузка праздников за 2020 (ограничение бесплатной версии)
     */
    fun loadHolidays() {
        disposable = holidaysProvider.getHolidaysByPeriod(Period(2020))
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doAfterTerminate { progressLiveData.postValue(false) }
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe(holidaysLiveData::setValue, errorLiveData::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.let { if (!it.isDisposed) it.isDisposed }
        disposable = null
    }
}