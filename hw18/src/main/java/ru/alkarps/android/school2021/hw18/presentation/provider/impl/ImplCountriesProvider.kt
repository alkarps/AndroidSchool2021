package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.country.CountryInteractor
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter
import javax.inject.Inject

/**
 * Реализация [CountriesProvider]
 *
 * @constructor Новый объект реализации [CountriesProvider]
 */
class ImplCountriesProvider @Inject constructor(
    private val countryInteractor: CountryInteractor,
    private val countryConverter: CountryConverter
) : CountriesProvider {
    override fun getCountries(): Single<List<CountryView>> =
        Single.fromCallable { countryInteractor.getCountries() }
            .map { countryConverter.countriesToView(it) }
}