package ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView
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
    private var subdivisionsDisposable: Disposable? = null
    val languagesLiveData = MutableLiveData<List<Language>>()
    val countriesLiveData = MutableLiveData<List<CountryView>>()
    val subdivisionsLiveData = MutableLiveData<List<SubdivisionView>>()
    val errorLiveData = MutableLiveData<Throwable>()

    /**
     * Метод загрузки данных для списков
     */
    fun loadAllData() {
        val main = schedulersProvider.main()
        val back = schedulersProvider.back()
        languagesDisposable = languagesProvider.getLanguages()
            .subscribeOn(back)
            .observeOn(main)
            .subscribe(languagesLiveData::setValue, errorLiveData::setValue)
        countriesDisposable = countriesProvider.getCountries()
            .subscribeOn(back)
            .observeOn(main)
            .subscribe(countriesLiveData::setValue, errorLiveData::setValue)
        subdivisionsDisposable = countriesProvider.getSubdivisionsForCurrentCountry()
            .subscribeOn(back)
            .observeOn(main)
            .subscribe(subdivisionsLiveData::setValue, errorLiveData::setValue)
    }

    /**
     * Метод обновления списка ТП
     *
     * @param country код страны
     */
    fun updateSubdivisions(country: String) {
        subdivisionsDisposable = countriesProvider.getSubdivisions(country)
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe(subdivisionsLiveData::setValue, errorLiveData::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        languagesDisposable = disposing(languagesDisposable)
        countriesDisposable = disposing(countriesDisposable)
        subdivisionsDisposable = disposing(subdivisionsDisposable)
    }

    private fun disposing(d: Disposable?): Disposable? {
        d?.let { if (!it.isDisposed) it.isDisposed }
        return null
    }
}