package ru.alkarps.android.school2021.hw18.data.language

import android.content.ContentValues
import androidx.core.database.sqlite.transaction
import ru.alkarps.android.school2021.hw18.data.di.DataScope
import ru.alkarps.android.school2021.hw18.data.storage.DBHelper
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language
import javax.inject.Inject

/**
 * Реализация [LanguageRepository]
 *
 * @property helper вспомогательный класс управления БД
 */
@DataScope
class ImplLanguageRepository @Inject constructor(
    private val helper: DBHelper
) : LanguageRepository {
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
            helper.writableDatabase.use { db ->
                db.transaction {
                    this.delete(DBHelper.LANGUAGE_TABLE, null, null)
                    languages.map {
                        ContentValues().apply {
                            put(DBHelper.LANGUAGE_CODE, it.code.lowercase())
                            put(DBHelper.LANGUAGE_NAME, it.name)
                        }
                    }.forEach {
                        this.insert(DBHelper.LANGUAGE_TABLE, null, it)
                    }
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
                arrayOf(code.lowercase()),
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