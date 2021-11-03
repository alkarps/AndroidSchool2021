package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel

class MainViewModelFactoryTest {
    @Test
    fun create() {
        assertThat(MainViewModelFactory(mockk(), mockk()).create(MainViewModel::class.java))
            .isNotNull
    }
}