package ru.alkarps.android.school2021.hw18.data.storage.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция БД с версии 1 на версию 2.
 * Используется для миграции с SQLiteOpenHelper на room
 */
object Migration_1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        recreateLanguageTable(database)
        recreateCountryTable(database)
    }

    private fun recreateLanguageTable(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `language_temp` (`code` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`code`))")
        database.execSQL("INSERT INTO language_temp SELECT * FROM language")
        database.execSQL("DROP TABLE language")
        database.execSQL("ALTER TABLE language_temp RENAME TO language")
    }

    private fun recreateCountryTable(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `country_temp` (`code` TEXT NOT NULL, `name` TEXT NOT NULL, `language_codes` TEXT NOT NULL, `flag` TEXT NOT NULL, PRIMARY KEY(`code`))")
        database.execSQL("CREATE TABLE IF NOT EXISTS `subdivision_temp` (`code` TEXT NOT NULL, `country_code` TEXT NOT NULL, `name` TEXT NOT NULL, `language_codes` TEXT NOT NULL, PRIMARY KEY(`code`))")
        database.execSQL("INSERT INTO country_temp SELECT * FROM country")
        database.execSQL("INSERT INTO subdivision_temp SELECT * FROM subdivision")
        database.execSQL("DROP TABLE subdivision")
        database.execSQL("DROP TABLE country")
        database.execSQL("ALTER TABLE country_temp RENAME TO country")
        database.execSQL("CREATE TABLE IF NOT EXISTS `subdivision` (`code` TEXT NOT NULL, `country_code` TEXT NOT NULL, `name` TEXT NOT NULL, `language_codes` TEXT NOT NULL, PRIMARY KEY(`code`), FOREIGN KEY(`country_code`) REFERENCES `country`(`code`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        database.execSQL("INSERT INTO subdivision SELECT * FROM subdivision_temp")
        database.execSQL("DROP TABLE subdivision_temp")
    }
}