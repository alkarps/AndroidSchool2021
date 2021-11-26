package ru.alkarps.android.school2021.hw18.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDatabase
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract
import ru.alkarps.android.school2021.hw18.data.storage.dao.CountryDao
import ru.alkarps.android.school2021.hw18.data.storage.dao.EventDao
import ru.alkarps.android.school2021.hw18.data.storage.dao.LanguageDao
import ru.alkarps.android.school2021.hw18.data.storage.migration.Migration_1_2
import ru.alkarps.android.school2021.hw18.data.storage.migration.Migration_2_3

@Module
class DataContextModule(context: Context) {
    private val holidayApiDatabase = Room.databaseBuilder(
        context,
        HolidayApiDatabase::class.java,
        HolidayApiDbContract.DB_FILE_NAME
    ).addMigrations(Migration_1_2, Migration_2_3).build()
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @DataScope
    fun database(): HolidayApiDatabase = holidayApiDatabase

    @Provides
    @DataScope
    fun languageDao(): LanguageDao = holidayApiDatabase.languageDao()

    @Provides
    @DataScope
    fun countryDao(): CountryDao = holidayApiDatabase.countryDao()

    @Provides
    @DataScope
    fun eventDao(): EventDao = holidayApiDatabase.eventDao()

    @Provides
    @DataScope
    fun preferences(): SharedPreferences = preferences
}