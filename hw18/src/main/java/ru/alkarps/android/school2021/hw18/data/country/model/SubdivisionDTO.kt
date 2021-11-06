package ru.alkarps.android.school2021.hw18.data.country.model

import kotlinx.serialization.Serializable

/**
 * Территориальное подразделение
 *
 * @property code [ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2) код подразделения
 * @property name название подразделения
 * @property languages языки, на которых разговаривают в данном подразделении
 */
@Serializable
data class SubdivisionDTO(
    val code: String,
    val name: String,
    val languages: List<String>
)
