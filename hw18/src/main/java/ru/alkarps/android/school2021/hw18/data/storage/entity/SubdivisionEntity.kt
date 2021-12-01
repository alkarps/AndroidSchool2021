package ru.alkarps.android.school2021.hw18.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract

/**
 * Представление ТП в БД
 *
 * @property code [ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2) код территориального подразделения
 * @property countryCode [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) код страны
 * @property name название территориального подразделения
 * @property languages коды языков, на которых разговаривают в территориальном подразделении
 * @constructor Create empty Subdivision entity
 */
@Entity(
    tableName = HolidayApiDbContract.Tables.SUBDIVISION,
    foreignKeys = [ForeignKey(
        entity = CountryEntity::class,
        parentColumns = [HolidayApiDbContract.Country.CODE],
        childColumns = [HolidayApiDbContract.Subdivision.COUNTRY_CODE],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubdivisionEntity(
    @PrimaryKey
    @ColumnInfo(name = HolidayApiDbContract.Subdivision.CODE)
    val code: String,
    @ColumnInfo(name = HolidayApiDbContract.Subdivision.COUNTRY_CODE)
    val countryCode: String,
    @ColumnInfo(name = HolidayApiDbContract.Subdivision.NAME)
    val name: String,
    @ColumnInfo(name = HolidayApiDbContract.Subdivision.LANGUAGES)
    val languages: String
)