package ru.alkarps.android.school2021.hw18.presentation.provider

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.DivisionView

/**
 * Провайдер стран и территориальных подразделений
 */
interface CountriesProvider {
    /**
     * Метод получения доступных стран и их ТП
     *
     * @return список доступных стран и их ТП
     */
    fun getCountries(): Single<List<CountryView>>
}