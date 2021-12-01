package ru.alkarps.android.school2021.hw18.data.country.model

import kotlinx.serialization.Serializable

/**
 * Страна
 *
 * @property code [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) код страны
 * @property name название страны
 * @property codes коды страны в других вариантах:
 * alpha-2 ([ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)),
 * alpha-3 ([ISO 3166-1 alpha-3](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3))
 * и numeric ([ISO 3166-1 numeric](https://en.wikipedia.org/wiki/ISO_3166-1_numeric))
 * @property languages языки, на которых разговаривают в данной стране
 * @property flag флаг страны
 * @property subdivisions территориальные подразделения страны
 */
@Serializable
data class CountryDTO(
    val code: String,
    val name: String,
    val codes: Map<String, String>?,
    val languages: List<String>,
    val flag: String,
    val subdivisions: List<SubdivisionDTO>
)
