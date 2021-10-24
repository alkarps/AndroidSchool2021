package ru.alkarps.android.school2021.hw18.data.holiday.model

import kotlinx.serialization.Serializable
import ru.alkarps.android.school2021.hw18.data.api.model.RequestsDTO

/**
 * Ответ от HolidayAPI
 *
 * @property status статус запроса
 * @property requests информация о доступных запросах
 * @property holidays праздники
 * @constructor Создает ответ
 */
@Serializable
data class HolidayResponseDTO(
    val status: Int,
    val requests: RequestsDTO?,
    val holidays: List<HolidayDTO>?
)
