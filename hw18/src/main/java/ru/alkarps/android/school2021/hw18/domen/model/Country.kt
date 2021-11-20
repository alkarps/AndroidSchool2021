package ru.alkarps.android.school2021.hw18.domen.model

/**
 * Страна
 *
 * @property code [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) код страны
 * @property name название страны
 * @property languageCodes коды языков, на которых разговаривают в стране
 * @property flag флаг страны
 */
data class Country(
    val code: String,
    val name: String,
    val languageCodes: List<String>,
    val flag: String,
    val subdivisions: List<Subdivision>
)
