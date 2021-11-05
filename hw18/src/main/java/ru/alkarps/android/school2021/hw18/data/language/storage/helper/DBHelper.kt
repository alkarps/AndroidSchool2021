package ru.alkarps.android.school2021.hw18.data.language.storage.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Класс управления версионированием БД и ее структурой.
 *
 * @param context контекст приложения
 * @constructor новый экземпляр класса
 */
class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    /**
     * Создание БД при первом запуске
     *
     * @param db БД
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_LANGUAGE_CREATE)
    }

    /**
     * Обновление ранее созданной версии при изменении структуры данных.
     *
     * На текущий момент не используется
     *
     * @param db БД
     * @param oldVersion номер старой версии
     * @param newVersion номер новой версии
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private const val DB_FILE_NAME = "holiday_api.db"
        private const val DB_VERSION = 1
        const val LANGUAGE_TABLE = "language"
        const val LANGUAGE_CODE = "code"
        const val LANGUAGE_NAME = "name"

        private const val SQL_LANGUAGE_CREATE = "CREATE TABLE IF NOT EXISTS $LANGUAGE_TABLE ( " +
                "$LANGUAGE_CODE TEXT PRIMARY KEY," +
                "$LANGUAGE_NAME TEXT NOT NULL)"
    }
}