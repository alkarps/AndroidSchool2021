package ru.alkarps.android.school2021.hw18.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract

/**
 * Представление языка в БД
 *
 * @property code код языка
 * @property name название языка
 * @constructor Create empty Language entity
 */
@Entity(tableName = HolidayApiDbContract.Tables.LANGUAGE)
data class LanguageEntity(
    @PrimaryKey
    @ColumnInfo(name = HolidayApiDbContract.Language.CODE)
    val code: String,
    @ColumnInfo(name = HolidayApiDbContract.Language.NAME)
    val name: String
)
