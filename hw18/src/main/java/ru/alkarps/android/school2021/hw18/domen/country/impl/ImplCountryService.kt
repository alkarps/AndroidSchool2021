package ru.alkarps.android.school2021.hw18.domen.country.impl

import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.CountryWithSubdivision
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository
import javax.inject.Inject

/**
 * Реализация [CountryService]
 *
 * @property settings настройки приложения
 * @property repository репозиторий с странами и их территориальными подразделениями
 * @property client клиент для получения стран и их территориальных подразделений
 */
class ImplCountryService @Inject constructor(
    private val settings: SettingsRepository,
    private val repository: CountryRepository,
    private val client: CountryClient
) : CountryService {
    override fun getCurrentSubdivision(): Subdivision {
        val code = settings.getCurrentSubdivisionCode()
        return repository.findSubdivision(code)
            ?: getSubdivisionsAndSave(settings.getCurrentCountryCode()).find { it.code == code }
            ?: Subdivision(UNKNOWN_CODE, UNKNOWN_CODE, emptyList())
    }

    override fun getCountries(): List<Country> {
        return repository.getCountries() ?: getCountryAndSave()
    }

    private fun getCountryAndSave(): List<Country> =
        getCountryWithSubdivisionsAndSave().map { it.country }

    override fun getSubdivisionsForCurrentCountry(): List<Subdivision> =
        getSubdivisions(settings.getCurrentCountryCode())

    override fun getSubdivisions(countryCode: String): List<Subdivision> {
        return repository.getSubdivisions(countryCode) ?: getSubdivisionsAndSave(countryCode)
    }

    private fun getSubdivisionsAndSave(countryCode: String): List<Subdivision> =
        getCountryWithSubdivisionsAndSave()
            .find { it.country.code == countryCode }
            ?.subdivisions ?: emptyList()

    private fun getCountryWithSubdivisionsAndSave(): List<CountryWithSubdivision> =
        client.getCountriesWithSubdivisions()
            .apply { repository.saveCountryWithSubdivisions(this) }

    companion object {
        const val UNKNOWN_CODE = "UNKNOWN"
    }
}