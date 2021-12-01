package ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.LanguagesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

/**
 * [ViewModel] для экрана настроек приложения
 *
 * @property schedulersProvider провайдер шедулеров
 * @property languagesProvider провайдер языков
 * @property countriesProvider провайдер стран и ТП
 * @constructor Новый инстанс [MainViewModel]
 */
class SettingsViewModel constructor(
    private val schedulersProvider: SchedulersProvider,
    private val languagesProvider: LanguagesProvider,
    private val countriesProvider: CountriesProvider
) : ViewModel() {
    private var languagesDisposable: Disposable? = null
    private var countriesDisposable: Disposable? = null
    private val languagesProgressLiveData = MutableLiveData<Boolean>()
    val languagesLiveData = MutableLiveData<List<Language>>()
    private val countriesProgressLiveData = MutableLiveData<Boolean>()
    val countriesLiveData = MutableLiveData<List<CountryView>>()
    val progressLiveData = MediatorLiveData<Boolean>().apply {
        val doCalculation = Observer<Boolean> {
            value =
                languagesProgressLiveData.value == true && countriesProgressLiveData.value == true
        }
        addSource(languagesProgressLiveData, doCalculation)
        addSource(countriesProgressLiveData, doCalculation)
    }
    val errorLiveData = MutableLiveData<Throwable>()

    /**
     * Метод загрузки данных для списков
     */
    fun loadAllData() {
        val main = schedulersProvider.main()
        val back = schedulersProvider.back()
        languagesDisposable = languagesProvider.getLanguages()
            .doOnSubscribe { languagesProgressLiveData.postValue(false) }
            .doAfterTerminate { languagesProgressLiveData.postValue(true) }
            .subscribeOn(back)
            .observeOn(main)
            .subscribe(languagesLiveData::setValue, errorLiveData::setValue)
        countriesDisposable = countriesProvider.getCountries()
            .doOnSubscribe { countriesProgressLiveData.postValue(false) }
            .doAfterTerminate { countriesProgressLiveData.postValue(true) }
            .subscribeOn(back)
            .observeOn(main)
            .subscribe(countriesLiveData::setValue, errorLiveData::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        languagesDisposable = disposing(languagesDisposable)
        countriesDisposable = disposing(countriesDisposable)
    }

    private fun disposing(d: Disposable?): Disposable? {
        d?.let { if (!it.isDisposed) it.isDisposed }
        return null
    }
}