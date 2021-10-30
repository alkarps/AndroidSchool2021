package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

class ImplSchedulersProviderTest {
    private lateinit var provider: SchedulersProvider

    @Before
    fun setUp() {
        provider = ImplSchedulersProvider()
    }

    @Test
    fun back() {
        assertThat(provider.back()).isNotNull
    }

    @Test
    fun main() {
        assertThat(provider.main()).isNotNull
    }
}