package ru.alkarps.android.school2021.hw29.domen.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw29.domen.repositories.models.DictionaryItemModel

interface IDictionaryRepository {
    fun add(dim: DictionaryItemModel): Completable
    fun getList(): Single<List<DictionaryItemModel>>
    fun delete(id: Long): Completable
}