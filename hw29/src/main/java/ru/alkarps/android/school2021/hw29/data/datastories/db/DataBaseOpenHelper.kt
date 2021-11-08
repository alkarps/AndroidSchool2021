package ru.alkarps.android.school2021.hw29.data.datastories.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private const val DB_NAME = "dictionary.db"
        private const val DB_VERSION = 1

        const val DICTIONARY_TABLE = "translations"
        const val DICTIONARY_ID = "_ID"
        const val DICTIONARY_KEYWORD = "keyword"
        const val DICTIONARY_TRANSLATION = "translation"

        private const val CREATE_TABLE = "CREATE TABLE $DICTIONARY_TABLE (" +
                " $DICTIONARY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " $DICTIONARY_TRANSLATION TEXT, " +
                " $DICTIONARY_KEYWORD TEXT " +
                ")"
    }
}