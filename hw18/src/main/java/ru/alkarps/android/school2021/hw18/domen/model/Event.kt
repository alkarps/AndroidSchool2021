package ru.alkarps.android.school2021.hw18.domen.model

/**
 * Событие
 *
 * @property uuid id события
 * @property name название события
 * @property date дата события
 * @property startTime время начала события, если событие не праздник
 * @property location место проведения события, если событие не праздник
 * @property observed дата отмечания праздника, если событие - праздник
 * @property public признак государственного праздника, если событие - праздник
 * @property readOnly только чтение
 */
data class Event(
    val uuid: String,
    val name: String,
    val date: String,
    val startTime: String? = null,
    val location: String? = null,
    val observed: String? = null,
    val public: Boolean? = null,
    val readOnly: Boolean = false
)
