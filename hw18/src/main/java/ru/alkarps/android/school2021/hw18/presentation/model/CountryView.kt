package ru.alkarps.android.school2021.hw18.presentation.model

/**
 * Модель для отображения страны на View
 *
 * @property country описание страны
 * @property divisions описание ТП страны
 */
data class CountryView(
    val country: DivisionView,
    val divisions: List<DivisionView>,
)
