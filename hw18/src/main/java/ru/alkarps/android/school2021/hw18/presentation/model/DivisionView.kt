package ru.alkarps.android.school2021.hw18.presentation.model
/**
 * Модель для отображения территориального подразделения или страны на View
 *
 * @property code код страны ISO 3166-1 alpha-2 или ТП ISO 3166-2
 * @property name название страныили ТП
 */
data class DivisionView(
    val code: String,
    val name: String
)
