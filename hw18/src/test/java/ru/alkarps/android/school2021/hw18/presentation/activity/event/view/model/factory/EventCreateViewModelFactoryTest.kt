package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventCreateViewModel

class EventCreateViewModelFactoryTest {
    @Test
    fun create() {
        assertThat(
            EventCreateViewModelFactory(mockk(), mockk())
                .create(EventCreateViewModel::class.java)
        ).isNotNull
    }
}