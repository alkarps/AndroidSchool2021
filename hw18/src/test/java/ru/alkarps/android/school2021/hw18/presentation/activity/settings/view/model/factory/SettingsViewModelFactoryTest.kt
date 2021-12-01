package ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.factory

import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.Test
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel

class SettingsViewModelFactoryTest {
    @Test
    fun create() {
        Assertions.assertThat(
            SettingsViewModelFactory(mockk(), mockk(), mockk())
                .create(SettingsViewModel::class.java)
        ).isNotNull
    }
}