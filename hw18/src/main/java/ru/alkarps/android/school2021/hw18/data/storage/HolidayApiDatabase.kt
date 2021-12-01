package ru.alkarps.android.school2021.hw18.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alkarps.android.school2021.hw18.data.storage.dao.CountryDao
import ru.alkarps.android.school2021.hw18.data.storage.dao.EventDao
import ru.alkarps.android.school2021.hw18.data.storage.dao.LanguageDao
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity

/**
 * Описание БД
 */
@Database(
    entities = [LanguageEntity::class, CountryEntity::class, SubdivisionEntity::class, EventEntity::class],
    version = HolidayApiDbContract.DB_VERSION
)
abstract class HolidayApiDatabase : RoomDatabase() {
    /**
     * Метод получения DAO для работы с странами и их ТП
     *
     * @return DAO
     */
    abstract fun countryDao(): CountryDao

    /**
     * Метод получения DAO для работы с языками
     *
     * @return DAO
     */
    abstract fun languageDao(): LanguageDao

    /**
     * Метод получения DAO для работы с языками
     *
     * @return DAO
     */
    abstract fun eventDao(): EventDao
}