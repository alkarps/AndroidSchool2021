package ru.alkarps.android.school2021.hw18.data.country

import android.content.ContentValues
import androidx.core.database.sqlite.transaction
import ru.alkarps.android.school2021.hw18.data.storage.DBHelper
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import javax.inject.Inject

/**
 * Реализация [CountryRepository]
 *
 * @property helper вспомогательный класс управления БД
 */
class ImplCountryRepository @Inject constructor(
    private val helper: DBHelper
) : CountryRepository {
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
                            it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_FLAG)),
                            getSubdivisions(it.getString(it.getColumnIndexOrThrow(DBHelper.COUNTRY_CODE)))
                        )
                    )
                }
            }
        }
        return result
    }

    private fun getSubdivisions(countryCode: String): List<Subdivision> {
        val result = mutableListOf<Subdivision>()
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
                    result.add(
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

    override fun saveCountries(countries: List<Country>) {
        if (countries.isNotEmpty()) {
            helper.writableDatabase.use { db ->
                db.transaction {
                    this.delete(DBHelper.SUBDIVISION_TABLE, null, null)
                    this.delete(DBHelper.COUNTRY_TABLE, null, null)

                    countries.map {
                        ContentValues().apply {
                            put(DBHelper.COUNTRY_CODE, it.code.lowercase())
                            put(DBHelper.COUNTRY_NAME, it.name)
                            put(
                                DBHelper.COUNTRY_LANGUAGES,
                                it.languageCodes.joinToString(",")
                            )
                            put(DBHelper.COUNTRY_FLAG, it.flag)
                        }
                    }.forEach { insert(DBHelper.COUNTRY_TABLE, null, it) }
                    countries.flatMap { cwd ->
                        cwd.subdivisions.map {
                            ContentValues().apply {
                                put(DBHelper.SUBDIVISION_CODE, it.code.lowercase())
                                put(DBHelper.SUBDIVISION_COUNTRY_CODE, cwd.code)
                                put(DBHelper.SUBDIVISION_NAME, it.name)
                                put(
                                    DBHelper.SUBDIVISION_LANGUAGES,
                                    it.languageCodes.joinToString(",")
                                )
                            }
                        }
                    }.forEach { insert(DBHelper.SUBDIVISION_TABLE, null, it) }
                }
            }
        }
    }
}