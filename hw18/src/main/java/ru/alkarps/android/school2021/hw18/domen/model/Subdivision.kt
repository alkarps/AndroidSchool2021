package ru.alkarps.android.school2021.hw18.domen.model

/**
 * Территориальное подразделение
 *
 * @property code [ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2) код территориального подразделения
 * @property name название территориального подразделения
 * @property languageCodes коды языков, на которых разговаривают в территориальном подразделении
 */
data class Subdivision(
    val code: String,
    val name: String,
    val languageCodes: List<String>
)
