package ru.alkarps.android.school2021.hw29.domen.interactors

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem

interface IDictionaryInteractor {
    fun add(dictionaryItem: DictionaryItem): Completable
    fun getList(): Single<List<DictionaryItem>>
    fun delete(id: Long): Completable
}