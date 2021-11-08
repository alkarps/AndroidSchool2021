package ru.alkarps.android.school2021.hw29.data.datastories.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import ru.alkarps.android.school2021.hw29.data.datastories.db.DataBaseOpenHelper
import ru.alkarps.android.school2021.hw29.data.datastories.db.DataBaseOpenHelper.Companion.DICTIONARY_TABLE

class DictionaryContentProvider() : ContentProvider() {
    private lateinit var helper: SQLiteOpenHelper
    override fun onCreate(): Boolean {
        helper = DataBaseOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        require(URI_MATCHER.match(uri) == PATH_TRANSLATIONS) { "Unknown URI: $uri" }
        return helper.readableDatabase.query(
            DICTIONARY_TABLE, projection, selection, selectionArgs, null, null, sortOrder
        )
    }

    override fun getType(uri: Uri): String? {
        require(URI_MATCHER.match(uri) == PATH_TRANSLATIONS) { "Unknown URI: $uri" }
        return DictionaryMetaData.TRANSLATES_CONTENT_TYPE
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        require(URI_MATCHER.match(uri) == PATH_TRANSLATIONS) { "Unknown URI: $uri" }
        helper.writableDatabase.insert(DICTIONARY_TABLE, null, values)
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        require(URI_MATCHER.match(uri) == PATH_TRANSLATIONS) { "Unknown URI: $uri" }
        return helper.writableDatabase.delete(DICTIONARY_TABLE, selection, selectionArgs)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    private companion object {
        private const val PATH_ROOT = 0
        private const val PATH_TRANSLATIONS = 1
        private val URI_MATCHER = UriMatcher(PATH_ROOT).apply {
            addURI(
                DictionaryMetaData.AUTHORITY,
                DictionaryMetaData.TRANSLATES_DATA_TYPE,
                PATH_TRANSLATIONS
            )
        }
    }
}