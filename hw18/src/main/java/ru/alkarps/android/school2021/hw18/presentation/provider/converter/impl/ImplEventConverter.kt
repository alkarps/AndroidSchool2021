package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import ru.alkarps.android.school2021.hw18.domen.model.Event
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.EventConverter
import javax.inject.Inject

/**
 * Реализация [EventConverter]
 *
 * @constructor Create empty Impl event converter
 */
class ImplEventConverter @Inject constructor() : EventConverter {
    override fun fromView(event: EventView): Event =
        Event(event.uuid, event.name, event.date, event.startTime, event.location)

    override fun toView(events: List<Event>): List<EventView> = events.map { toView(it) }

    private fun toView(event: Event): EventView =
        EventView(event.name, event.date, event.startTime, event.location, event.uuid)
}