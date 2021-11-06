package ru.alkarps.android.school2021.hw18.data.country.model

import kotlinx.serialization.Serializable

/**
 * Варианты кодов страны
 *
 * @property alpha-2 [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
 * @property alpha-3 [ISO 3166-1 alpha-3](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3)
 * @property numeric [ISO 3166-1 numeric](https://en.wikipedia.org/wiki/ISO_3166-1_numeric)
 */
@Serializable
data class CountryCodesDTO(
    val `alpha-2`: String,
    val `alpha-3`: String,
    val numeric: String
)
