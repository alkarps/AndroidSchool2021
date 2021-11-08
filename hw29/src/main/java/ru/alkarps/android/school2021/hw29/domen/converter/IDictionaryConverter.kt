package ru.alkarps.android.school2021.hw29.domen.converter

import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem
import ru.alkarps.android.school2021.hw29.domen.repositories.models.DictionaryItemModel

interface IDictionaryConverter {
    fun toModel(di: DictionaryItem): DictionaryItemModel
    fun fromModel(dim: DictionaryItemModel): DictionaryItem
    fun fromModel(dims: List<DictionaryItemModel>): List<DictionaryItem>
}