package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.country.CountryService
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.CountryConverter

/**
 * Реализация [CountriesProvider]
 *
 * @constructor Новый объект реализации [CountriesProvider]
 */
class ImplCountriesProvider(
    private val countryService: CountryService,
    private val countryConverter: CountryConverter
) : CountriesProvider {
    override fun getCountries(): Single<List<CountryView>> =
        Single.fromCallable { countryService.getCountries() }
            .map { countryConverter.countriesToView(it) }

    override fun getSubdivisionsForCurrentCountry(): Single<List<SubdivisionView>> =
        Single.fromCallable { countryService.getSubdivisionsForCurrentCountry() }
            .map { countryConverter.subdivisionToView(it) }

    override fun getSubdivisions(country: String): Single<List<SubdivisionView>> =
        Single.fromCallable { countryService.getSubdivisions(country) }
            .map { countryConverter.subdivisionToView(it) }
}