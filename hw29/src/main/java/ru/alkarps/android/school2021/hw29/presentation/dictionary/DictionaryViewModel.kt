package ru.alkarps.android.school2021.hw29.presentation.dictionary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.alkarps.android.school2021.hw29.domen.interactors.IDictionaryInteractor
import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem
import ru.alkarps.android.school2021.hw29.presentation.providers.SchedulersProvider

class DictionaryViewModel(
    private val dictionaryInteractor: IDictionaryInteractor,
    private val schedulersProvider: SchedulersProvider
) : ViewModel() {
    val finishing = MutableLiveData<Boolean>()
    val dictionaryItems = MutableLiveData<List<DictionaryItem>>()
    val errors = MutableLiveData<Throwable>()
    private var dictionaryDisposable: Disposable? = null

    fun insertWord(di: DictionaryItem) {
        dictionaryInteractor.add(di)
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe({ finishing.value = true }, errors::setValue)
    }

    fun removeWord(id: Long) {
        dictionaryInteractor.delete(id)
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe({ finishing.value = true }, errors::setValue)
    }

    fun loadDataRx() {
        dictionaryDisposable = dictionaryInteractor.getList()
            .subscribeOn(schedulersProvider.back())
            .observeOn(schedulersProvider.main())
            .subscribe(dictionaryItems::setValue, errors::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        dictionaryDisposable?.apply { if (!isDisposed) dispose() }
        dictionaryDisposable = null
    }
}