package ru.alkarps.android.school2021.hw18.data.language.model

import kotlinx.serialization.Serializable
import ru.alkarps.android.school2021.hw18.data.api.model.RequestsDTO

/**
 * Ответ от HolidayAPI
 *
 * @property status статус запроса
 * @property requests информация о доступных запросах
 * @property languages доступные языки
 * @constructor Создает ответ
 */
@Serializable
data class LanguagesResponseDTO(
    val status: Int,
    val requests: RequestsDTO?,
    val languages: List<LanguageDTO>?
)
