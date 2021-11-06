package ru.alkarps.android.school2021.hw18.data.country

import android.content.ContentValues
import androidx.core.database.sqlite.transaction
import ru.alkarps.android.school2021.hw18.data.storage.DBHelper
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision

/**
 * Реализация [CountryRepository]
 *
 * @property helper вспомогательный класс управления БД
 */
class ImplCountryRepository(private val helper: DBHelper) : CountryRepository {
    override fun getCountries(): List<Country>? {
        var result: MutableList<Country>? = null
        helper.readableDatabase.use { db ->
            db.query(
                DBHelper.COUNTRY_TABLE,
                arrayOf(
                    DBHelper.COUNTRY_CODE,
                    DBHelper.COUNTRY_NAME,
                    DBHelper.COUNTRY_LANGUAGES,
                    DBHelper.COUNTRY_FLAG
                ),
                null,
                null,
                null,
                null,
                null,
                null
            ).use {
                while (it.moveToNext()) {
                    if (result == null) result = mutableListOf()
                    result?.add(
                        Country(
                            it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_CODE)),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_NAME)),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_LANGUAGES))
                                .split(","),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_FLAG))
                        )
                    )
                }
            }
        }
        return result
    }

    override fun getSubdivisions(countryCode: String): List<Subdivision>? {
        var result: MutableList<Subdivision>? = null
        helper.readableDatabase.use { db ->
            db.query(
                DBHelper.SUBDIVISION_TABLE,
                arrayOf(
                    DBHelper.SUBDIVISION_CODE,
                    DBHelper.SUBDIVISION_NAME,
                    DBHelper.SUBDIVISION_LANGUAGES
                ),
                "${DBHelper.SUBDIVISION_COUNTRY_CODE} = ?",
                arrayOf(countryCode.lowercase()),
                null,
                null,
                null,
                null
            ).use {
                while (it.moveToNext()) {
                    if (result == null) result = mutableListOf()
                    result?.add(
                        Subdivision(
                            it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_CODE)),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_NAME)),
                            it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_LANGUAGES))
                                .split(","),
                        )
                    )
                }
            }
        }
        return result
    }

    override fun findSubdivision(subdivisionCode: String): Subdivision? {
        helper.readableDatabase.use { db ->
            db.query(
                DBHelper.SUBDIVISION_TABLE,
                arrayOf(
                    DBHelper.SUBDIVISION_CODE,
                    DBHelper.SUBDIVISION_NAME,
                    DBHelper.SUBDIVISION_LANGUAGES
                ),
                "${DBHelper.SUBDIVISION_CODE} = ?",
                arrayOf(subdivisionCode.lowercase()),
                null,
                null,
                null,
                null
            ).use {
                while (it.moveToNext()) {
                    return Subdivision(
                        it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_CODE)),
                        it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_NAME)),
                        it.getString(it.getColumnIndexOrThrow(DBHelper.SUBDIVISION_LANGUAGES))
                            .split(","),
                    )
                }
            }
        }
        return null
    }

    override fun saveCountryWithSubdivisions(countriesWithSubdivision: List<CountryWithSubdivision>) {
        if (countriesWithSubdivision.isNotEmpty()) {
            val countries = ContentValues()
            val subdivisions = ContentValues()
            countriesWithSubdivision.forEach {
                countries.put(DBHelper.COUNTRY_CODE, it.country.code.lowercase())
                countries.put(DBHelper.COUNTRY_NAME, it.country.name)
                countries.put(
                    DBHelper.COUNTRY_LANGUAGES,
                    it.country.languageCodes.joinToString(",")
                )
                countries.put(DBHelper.COUNTRY_FLAG, it.country.flag)
                it.subdivisions.forEach { d ->
                    subdivisions.put(DBHelper.SUBDIVISION_CODE, d.code.lowercase())
                    subdivisions.put(DBHelper.SUBDIVISION_COUNTRY_CODE, it.country.code)
                    subdivisions.put(DBHelper.SUBDIVISION_NAME, d.name)
                    subdivisions.put(
                        DBHelper.SUBDIVISION_LANGUAGES,
                        d.languageCodes.joinToString(",")
                    )
                }
            }
            helper.writableDatabase.use {
                it.transaction {
                    this.delete(DBHelper.SUBDIVISION_TABLE, null, null)
                    this.delete(DBHelper.COUNTRY_TABLE, null, null)
                    this.insert(DBHelper.COUNTRY_TABLE, null, countries)
                    this.insert(DBHelper.SUBDIVISION_TABLE, null, subdivisions)
                }
            }
        }
    }
}