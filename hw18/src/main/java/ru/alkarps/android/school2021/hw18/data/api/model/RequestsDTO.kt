package ru.alkarps.android.school2021.hw18.data.api.model

import kotlinx.serialization.Serializable

/**
 * Информация о доступных запросах
 *
 * @property available количество доступных запросов в текущем периоде
 * @property used количество выполненных запросов в текущем периоде
 * @property resets когда сбросится счетчик доступных запросов
 * @constructor Новый объект
 */
@Serializable
data class RequestsDTO(
    val available: Int,
    val used: Int,
    val resets: String
)
