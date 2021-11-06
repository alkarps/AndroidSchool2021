package ru.alkarps.android.school2021.hw18.presentation.provider

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView

/**
 * Провайдер стран и территориальных подразделений
 */
interface CountriesProvider {
    /**
     * Метод получения доступных стран
     *
     * @return список доступных стран
     */
    fun getCountries(): Single<List<CountryView>>

    /**
     * Метод получения доступных для текущей страны территориальных подразделений
     *
     * @return список доступных для страны территориальных подразделений
     */
    fun getSubdivisionsForCurrentCountry(): Single<List<SubdivisionView>>

    /**
     * Метод получения доступных для страны территориальных подразделений
     *
     * @param country - код страны ISO 3166-1 alpha-2
     * @return список доступных для страны территориальных подразделений
     */
    fun getSubdivisions(country: String): Single<List<SubdivisionView>>
}