package ru.alkarps.android.school2021.hw18.data.country

import ru.alkarps.android.school2021.hw18.data.country.converter.CountryConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.CountryDao
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import javax.inject.Inject

/**
 * Реализация [CountryRepository]
 *
 * @property dao DAO класс управления БД для работы с доступными странами и ТП
 * @property converter конвертер доступных стрна и ТП в дата слое
 */
class ImplCountryRepository @Inject constructor(
    private val dao: CountryDao,
    private val converter: CountryConverter
) : CountryRepository {
    override fun getCountries(): List<Country>? {
        return dao.getCountries().map { converter.fromEntity(it) }
            .let { if (it.isEmpty()) null else it }
    }

    override fun findSubdivision(subdivisionCode: String): Subdivision? {
        return dao.findSubdivision(subdivisionCode.lowercase())?.let { converter.fromEntity(it) }
    }

    override fun saveCountries(countries: List<Country>) {
        dao.insert(countries.map { converter.toEntity(it) })
    }
}