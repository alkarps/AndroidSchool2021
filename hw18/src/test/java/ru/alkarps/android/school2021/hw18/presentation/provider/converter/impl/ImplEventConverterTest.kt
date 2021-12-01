package ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.model.Event
import ru.alkarps.android.school2021.hw18.presentation.model.EventView

class ImplEventConverterTest {
    private val event = Event("uuid", "name", "date", "startTime", "location")
    private val eventView = EventView("name", "date", "startTime", "location", "uuid")

    @Test
    fun fromView() {
        assertThat(ImplEventConverter().fromView(eventView)).isEqualTo(event)
    }

    @Test
    fun toView() {
        assertThat(ImplEventConverter().toView(listOf(event))).isEqualTo(listOf(eventView))
    }
}