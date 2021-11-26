package ru.alkarps.android.school2021.hw18.data.event.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
import ru.alkarps.android.school2021.hw18.domen.model.Event

class ImplEventConverterTest {
    private val uuid = "Uuid"
    private val event = Event(uuid, "Name", "Date", "StartTime", "Location")
    private val eventEntity = EventEntity(uuid, "Name", "Date", "StartTime", "Location")

    @Test
    fun toEntity() {
        assertThat(ImplEventConverter().toEntity(uuid)).isEqualTo(EventEntity(uuid, "", ""))
    }

    @Test
    fun testToEntity() {
        assertThat(ImplEventConverter().toEntity(event)).isEqualTo(eventEntity)
    }

    @Test
    fun fromEntity() {
        assertThat(ImplEventConverter().fromEntity(eventEntity)).isEqualTo(event)
    }
}