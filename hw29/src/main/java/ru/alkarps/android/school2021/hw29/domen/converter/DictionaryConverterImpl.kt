package ru.alkarps.android.school2021.hw29.domen.converter

import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem
import ru.alkarps.android.school2021.hw29.domen.repositories.models.DictionaryItemModel

class DictionaryConverterImpl : IDictionaryConverter {
    //TODO фикс id
    override fun toModel(di: DictionaryItem): DictionaryItemModel =
        DictionaryItemModel(0, di.keyword, di.translation)

    override fun fromModel(dim: DictionaryItemModel): DictionaryItem =
        DictionaryItem(dim.keyword, dim.translation, dim.id)

    override fun fromModel(dims: List<DictionaryItemModel>): List<DictionaryItem> =
        dims.map { fromModel(it) }
}