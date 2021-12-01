package ru.alkarps.android.school2021.hw18.data.storage.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция БД с версии 2 на версию 3.
 * Добавлена новая таблица event
 */
object Migration_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `event` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `date` TEXT NOT NULL, `start_time` TEXT, `location` TEXT, PRIMARY KEY(`uuid`))")
        database.execSQL("CREATE INDEX IF NOT EXISTS `index_event_date` ON `event` (`date`)")
    }
}