package ru.alkarps.android.school2021.hw18.data.country.model

import kotlinx.serialization.Serializable
import ru.alkarps.android.school2021.hw18.data.api.model.RequestsDTO

/**
 * Ответ от HolidayAPI
 *
 * @property status статус запроса
 * @property requests информация о доступных запросах
 * @property countries доступные страны
 * @constructor Создает ответ
 */
@Serializable
data class CountriesResponseDTO(
    val status: Int,
    val requests: RequestsDTO?,
    val countries: List<CountryDTO>?
)
