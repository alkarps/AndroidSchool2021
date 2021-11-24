package ru.alkarps.android.school2021.hw18.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract

/**
 * Представление страны в БД
 *
 * @property code [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) код страны
 * @property name название страны
 * @property languages коды языков, на которых разговаривают в стране
 * @property flag флаг страны
 * @constructor Create empty Country entity
 */
@Entity(tableName = HolidayApiDbContract.Tables.COUNTRY)
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = HolidayApiDbContract.Country.CODE)
    val code: String,
    @ColumnInfo(name = HolidayApiDbContract.Country.NAME)
    val name: String,
    @ColumnInfo(name = HolidayApiDbContract.Country.LANGUAGES)
    val languages: String,
    @ColumnInfo(name = HolidayApiDbContract.Country.FLAG)
    val flag: String
)
