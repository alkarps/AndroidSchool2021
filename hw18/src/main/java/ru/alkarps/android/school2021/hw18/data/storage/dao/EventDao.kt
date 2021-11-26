package ru.alkarps.android.school2021.hw18.data.storage.dao

import androidx.room.*
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract
import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
/**
 * DAO для работы с обытиями
 */
@Dao
interface EventDao {
    /**
     * Метод получения событий на указанную дату
     *
     * @param date дата события
     * @return список событий
     */
    @Query("SELECT * FROM ${HolidayApiDbContract.Tables.EVENT} WHERE ${HolidayApiDbContract.Event.DATE} = :date")
    fun getByDate(date: String): List<EventEntity>

    /**
     * Метод создания нового или обновление старого события
     *
     * @param event событие
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(event: EventEntity)

    /**
     * Метод удаления события
     *
     * @param event событие
     */
    @Delete
    fun delete(event: EventEntity)
}