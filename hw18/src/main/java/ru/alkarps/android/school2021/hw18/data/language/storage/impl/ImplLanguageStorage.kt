package ru.alkarps.android.school2021.hw18.data.language.storage.impl

import android.content.ContentValues
import androidx.core.database.sqlite.transaction
import ru.alkarps.android.school2021.hw18.data.language.storage.LanguageStorage
import ru.alkarps.android.school2021.hw18.data.language.storage.helper.DBHelper
import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Реализация [LanguageStorage]
 *
 * @property helper вспомогательный класс управления БД
 */
class ImplLanguageStorage(private val helper: DBHelper) : LanguageStorage {
    override fun getLanguages(): List<Language>? {
        var result: MutableList<Language>? = null
        helper.readableDatabase.use { db ->
            db.query(
                DBHelper.LANGUAGE_TABLE,
                arrayOf(DBHelper.LANGUAGE_CODE, DBHelper.LANGUAGE_NAME),
                null,
                null,
                null,
                null,
                null,
                null
            ).use {
                while (it.moveToNext()) {
                    if (result == null) result = mutableListOf()
                    result?.add(
                        Language(
                            it.getString(it.getColumnIndexOrThrow(DBHelper.LANGUAGE_CODE)),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.LANGUAGE_NAME))
                        )
                    )
                }
            }
        }
        return result
    }

    override fun saveLanguages(languages: List<Language>) {
        if (languages.isNotEmpty()) {
            val values = ContentValues().apply {
                languages.forEach {
                    put(DBHelper.LANGUAGE_CODE, it.code)
                    put(DBHelper.LANGUAGE_NAME, it.name)
                }
            }
            helper.writableDatabase.use {
                it.transaction {
                    this.delete(DBHelper.LANGUAGE_TABLE, null, null)
                    this.insert(DBHelper.LANGUAGE_TABLE, null, values)
                }
            }
        }
    }

    override fun findLanguage(code: String): Language? {
        helper.readableDatabase.use { db ->
            db.query(
                DBHelper.LANGUAGE_TABLE,
                arrayOf(DBHelper.LANGUAGE_CODE, DBHelper.LANGUAGE_NAME),
                "${DBHelper.LANGUAGE_CODE} = ?",
                arrayOf(code),
                null,
                null,
                null,
                null
            ).use {
                while (it.moveToNext()) {
                    return Language(
                        it.getString(it.getColumnIndexOrThrow(DBHelper.LANGUAGE_CODE)),
                        it.getString(it.getColumnIndexOrThrow(DBHelper.LANGUAGE_NAME))
                    )
                }
            }
        }
        return null
    }
}