package ru.alkarps.android.school2021.hw29.domen.interactors

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw29.domen.converter.IDictionaryConverter
import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem
import ru.alkarps.android.school2021.hw29.domen.repositories.IDictionaryRepository

class DictionaryInteractorImpl(
    private val repository: IDictionaryRepository,
    private val converter: IDictionaryConverter
) : IDictionaryInteractor {
    override fun add(dictionaryItem: DictionaryItem): Completable =
        repository.add(converter.toModel(dictionaryItem))

    override fun getList(): Single<List<DictionaryItem>> =
        repository.getList().map { converter.fromModel(it) }

    override fun delete(id: Long): Completable = repository.delete(id)
}