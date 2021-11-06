package ru.alkarps.android.school2021.hw18.domen.model

/**
 * Страна с территориальными подраделениями
 *
 * @property country описание страны
 * @property subdivisions список территориальных подразделений
 */
data class CountryWithSubdivision(
    val country: Country,
    val subdivisions: List<Subdivision>
)