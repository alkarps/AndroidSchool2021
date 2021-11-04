package ru.alkarps.android.school2021.hw18.data.language.model

import kotlinx.serialization.Serializable

/**
 * Доступный язык
 *
 * @property code код языка. [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes),
 * [ISO-639-2](https://en.wikipedia.org/wiki/List_of_ISO_639-2_codes) (для Cebuano, Hawaiian and Hmong)
 * или [BCP-47](https://en.wikipedia.org/wiki/IETF_language_tag) (для Китая)
 * @property name название языка
 * @constructor Создает ответ
 */
@Serializable
data class LanguageDTO(
    val code: String,
    val name: String
)
