package ru.alkarps.android.school2021.hw18.presentation.model

/**
 * Модель для отображения страны на View
 *
 * @property code код страны ISO 3166-1 alpha-2
 * @property name название страны
 */
data class CountryView(
    val code: String,
    val name: String
)
