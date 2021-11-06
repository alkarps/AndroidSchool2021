package ru.alkarps.android.school2021.hw18.data.storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.sqlite.transaction
import javax.inject.Inject

/**
 * Класс управления версионированием БД и ее структурой.
 *
 * @param context контекст приложения
 * @constructor новый экземпляр класса
 */
class DBHelper @Inject constructor(
    context: Context?
) : SQLiteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    /**
     * Создание БД при первом запуске
     *
     * @param db БД
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.transaction {
            this.execSQL(SQL_LANGUAGE_CREATE)
            this.execSQL(SQL_COUNTRY_CREATE)
            this.execSQL(SQL_SUBDIVISION_CREATE)
        }
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
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val DB_FILE_NAME = "holiday_api.db"
        private const val DB_VERSION = 1

        const val LANGUAGE_TABLE = "language"
        const val LANGUAGE_CODE = "code"
        const val LANGUAGE_NAME = "name"

        const val COUNTRY_TABLE = "country"
        const val COUNTRY_CODE = "code"
        const val COUNTRY_NAME = "name"
        const val COUNTRY_LANGUAGES = "language_codes"
        const val COUNTRY_FLAG = "flag"

        const val SUBDIVISION_TABLE = "subdivision"
        const val SUBDIVISION_CODE = "code"
        const val SUBDIVISION_COUNTRY_CODE = "country_code"
        const val SUBDIVISION_NAME = "name"
        const val SUBDIVISION_LANGUAGES = "language_codes"

        private const val SQL_LANGUAGE_CREATE = "CREATE TABLE IF NOT EXISTS $LANGUAGE_TABLE ( " +
                "$LANGUAGE_CODE TEXT PRIMARY KEY, " +
                "$LANGUAGE_NAME TEXT NOT NULL )"

        private const val SQL_COUNTRY_CREATE = "CREATE TABLE IF NOT EXISTS $COUNTRY_TABLE ( " +
                "$COUNTRY_CODE TEXT PRIMARY KEY, " +
                "$COUNTRY_NAME TEXT NOT NULL, " +
                "$COUNTRY_LANGUAGES TEXT NOT NULL, " +
                "$COUNTRY_FLAG TEXT NOT NULL )"

        private const val SQL_SUBDIVISION_CREATE =
            "CREATE TABLE IF NOT EXISTS $SUBDIVISION_TABLE ( " +
                    "$SUBDIVISION_CODE TEXT PRIMARY KEY, " +
                    "$SUBDIVISION_COUNTRY_CODE TEXT NOT NULL, " +
                    "$SUBDIVISION_NAME TEXT NOT NULL, " +
                    "$SUBDIVISION_LANGUAGES TEXT NOT NULL," +
                    "FOREIGN KEY($SUBDIVISION_COUNTRY_CODE) REFERENCES $COUNTRY_TABLE($COUNTRY_CODE) )"
    }
}