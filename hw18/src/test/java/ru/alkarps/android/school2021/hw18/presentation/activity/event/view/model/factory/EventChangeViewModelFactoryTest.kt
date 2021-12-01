package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.factory

import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.Test
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventChangeViewModel

class EventChangeViewModelFactoryTest {
    @Test
    fun create() {
        Assertions.assertThat(
            EventChangeViewModelFactory(mockk(), mockk())
                .create(EventChangeViewModel::class.java)
        ).isNotNull
    }
}