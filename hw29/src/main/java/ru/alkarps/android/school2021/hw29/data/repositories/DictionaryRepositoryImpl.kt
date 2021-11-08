package ru.alkarps.android.school2021.hw29.data.repositories

import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw29.data.datastories.db.DataBaseOpenHelper.Companion.DICTIONARY_ID
import ru.alkarps.android.school2021.hw29.data.datastories.db.DataBaseOpenHelper.Companion.DICTIONARY_KEYWORD
import ru.alkarps.android.school2021.hw29.data.datastories.db.DataBaseOpenHelper.Companion.DICTIONARY_TRANSLATION
import ru.alkarps.android.school2021.hw29.data.datastories.providers.DictionaryMetaData
import ru.alkarps.android.school2021.hw29.domen.repositories.IDictionaryRepository
import ru.alkarps.android.school2021.hw29.domen.repositories.models.DictionaryItemModel

class DictionaryRepositoryImpl(
    private val contentResolver: ContentResolver
) : IDictionaryRepository {
    override fun add(dim: DictionaryItemModel): Completable =
        Completable.fromAction {
            contentResolver.insert(
                DictionaryMetaData.TRANSLATES_CONTENT_URI,
                ContentValues().apply {
                    put(DICTIONARY_KEYWORD, dim.keyword)
                    put(DICTIONARY_TRANSLATION, dim.translation)
                }
            )
        }

    override fun getList(): Single<List<DictionaryItemModel>> = Single.fromCallable {
        val items = mutableListOf<DictionaryItemModel>()
        contentResolver.query(
            DictionaryMetaData.TRANSLATES_CONTENT_URI, null, null, null, null
        )?.use {
            while (it.moveToNext()) {
                items.add(toModel(it))
            }
        }
        items
    }

    private fun toModel(it: Cursor): DictionaryItemModel = DictionaryItemModel(
        it.getLong(it.getColumnIndexOrThrow(DICTIONARY_ID)),
        it.getString(it.getColumnIndexOrThrow(DICTIONARY_KEYWORD)),
        it.getString(it.getColumnIndexOrThrow(DICTIONARY_TRANSLATION)),
    )

    override fun delete(id: Long): Completable = Completable.fromAction {
        contentResolver.delete(
            DictionaryMetaData.TRANSLATES_CONTENT_URI,
            "$DICTIONARY_ID = ?",
            arrayOf(id.toString())
        )
    }
}