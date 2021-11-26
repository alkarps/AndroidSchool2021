package ru.alkarps.android.school2021.hw18.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract

/**
 * Описание события
 *
 * @property uuid айди события
 * @property name название события
 * @property date дата события
 * @property startTime время начала события
 * @property location локация события
 */
@Entity(tableName = HolidayApiDbContract.Tables.EVENT)
data class EventEntity(
    @PrimaryKey
    @ColumnInfo(name = HolidayApiDbContract.Event.UUID)
    val uuid: String,
    @ColumnInfo(name = HolidayApiDbContract.Event.NAME)
    val name: String,
    @ColumnInfo(name = HolidayApiDbContract.Event.DATE, index = true)
    val date: String,
    @ColumnInfo(name = HolidayApiDbContract.Event.START_TIME)
    val startTime: String? = null,
    @ColumnInfo(name = HolidayApiDbContract.Event.LOCATION, )
    val location: String? = null
)
