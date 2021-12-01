package ru.alkarps.android.school2021.hw18.data.event.converter.impl

import ru.alkarps.android.school2021.hw18.data.event.converter.EventConverter
import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
import ru.alkarps.android.school2021.hw18.domen.model.Event
import javax.inject.Inject

/**
 * Реализация [EventConverter]
 */
class ImplEventConverter @Inject constructor() : EventConverter {
    override fun toEntity(uuid: String): EventEntity = EventEntity(uuid, "", "")

    override fun toEntity(event: Event): EventEntity =
        EventEntity(event.uuid, event.name, event.date, event.startTime, event.location)

    override fun fromEntity(event: EventEntity): Event =
        Event(event.uuid, event.name, event.date, event.startTime, event.location)
}