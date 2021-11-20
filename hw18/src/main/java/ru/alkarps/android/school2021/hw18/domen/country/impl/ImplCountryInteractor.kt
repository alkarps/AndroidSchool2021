package ru.alkarps.android.school2021.hw18.domen.country.impl

import ru.alkarps.android.school2021.hw18.domen.country.CountryClient
import ru.alkarps.android.school2021.hw18.domen.country.CountryRepository
import ru.alkarps.android.school2021.hw18.domen.country.CountryInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Country
import ru.alkarps.android.school2021.hw18.domen.model.Subdivision
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository
import javax.inject.Inject

/**
 * Реализация [CountryInteractor]
 *
 * @property settings настройки приложения
 * @property repository репозиторий с странами и их территориальными подразделениями
 * @property client клиент для получения стран и их территориальных подразделений
 */
class ImplCountryInteractor @Inject constructor(
    private val settings: SettingsRepository,
    private val repository: CountryRepository,
    private val client: CountryClient
) : CountryInteractor {
    override fun getCurrentSubdivision(): Subdivision {
        val code = settings.getCurrentSubdivisionCode()
        return repository.findSubdivision(code)
            ?: getSubdivisionsAndSave(settings.getCurrentCountryCode()).find { it.code == code }
            ?: Subdivision(UNKNOWN_CODE, UNKNOWN_CODE, emptyList())
    }

    override fun getCountries(): List<Country> = repository.getCountries() ?: getCountryAndSave()

    private fun getSubdivisionsAndSave(countryCode: String): List<Subdivision> =
        getCountryAndSave().find { it.code == countryCode }?.subdivisions ?: emptyList()

    private fun getCountryAndSave(): List<Country> =
        client.getCountries().apply { repository.saveCountries(this) }

    companion object {
        const val UNKNOWN_CODE = "UNKNOWN"
    }
}