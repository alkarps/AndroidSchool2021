package ru.alkarps.android.school2021.hw18.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity

/**
 * DAO для работы с языками
 */
@Dao
interface LanguageDao {
    /**
     * Метод удаления всех доступных языков
     *
     */
    @Query("DELETE FROM ${HolidayApiDbContract.Tables.LANGUAGE}")
    fun deleteAll()

    /**
     * Метод добавления новых доступных языков
     *
     * @param languages список доступных языков
     */
    @Insert
    fun insertAll(languages: List<LanguageEntity>)

    /**
     * Метод получения всех доступных языков
     *
     * @return список доступных языков
     */
    @Query("SELECT * FROM ${HolidayApiDbContract.Tables.LANGUAGE}")
    fun getAll(): List<LanguageEntity>

    /**
     * Поиск языка по его коду
     *
     * @param code код искомого языка
     * @return доступный язык или null, если не найдено
     */
    @Query("SELECT * FROM ${HolidayApiDbContract.Tables.LANGUAGE} WHERE ${HolidayApiDbContract.Language.CODE} = :code")
    fun findByCode(code: String): LanguageEntity?
}