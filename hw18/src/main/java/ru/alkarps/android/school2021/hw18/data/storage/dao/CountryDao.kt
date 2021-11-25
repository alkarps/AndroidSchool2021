package ru.alkarps.android.school2021.hw18.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.CountryWithSubdivisionsEntity
import ru.alkarps.android.school2021.hw18.data.storage.entity.SubdivisionEntity

/**
 * DAO для работы с странами и ТП
 */
@Dao
interface CountryDao {
    /**
     * Метод получения стран и их ТП
     *
     * @return список доступных стран и их ТП
     */
    @Transaction
    @Query("SELECT * FROM ${HolidayApiDbContract.Tables.COUNTRY}")
    fun getCountries(): List<CountryWithSubdivisionsEntity>

    /**
     * Метод поиска ТП по коду
     *
     * @param code код ТП
     * @return представление ТП или null, если оно не найдено
     */
    @Query("SELECT * FROM ${HolidayApiDbContract.Tables.SUBDIVISION} WHERE ${HolidayApiDbContract.Subdivision.CODE} = :code")
    fun findSubdivision(code: String): SubdivisionEntity?

    /**
     * Метод очистки таблиц стран и ТП
     */
    @Query("DELETE FROM ${HolidayApiDbContract.Tables.COUNTRY}")
    fun deleteAll()

    /**
     * Добавление новых стран
     *
     * @param countries список доступных стран
     */
    @Insert
    fun insertCountry(countries: List<CountryEntity>)

    /**
     * Добавление новых ТП
     *
     * @param subdivisions список доступных ТП
     */
    @Insert
    fun insertSubdivisions(subdivisions: List<SubdivisionEntity>)

    /**
     * Метод добавления стран и их ТП.
     * Внимание, метод очищает таблицы перед добавлением новые страны и их ТП.
     *
     * @param countries список доступных стран и их ТП
     */
    @Transaction
    fun insert(countries: List<CountryWithSubdivisionsEntity>) {
        deleteAll()
        insertCountry(countries.map { it.country })
        insertSubdivisions(countries.flatMap { it.subdivisions })
    }
}