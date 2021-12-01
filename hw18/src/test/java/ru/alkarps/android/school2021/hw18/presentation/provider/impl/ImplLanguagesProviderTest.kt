package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguagesProviderTest {
    @Test
    fun getLanguages() {
        val expected = listOf(Language("", ""))

        val service = mockk<LanguageInteractor>()
        every { service.getLanguages() } returns expected

        ImplLanguagesProvider(service)
            .getLanguages()
            .test()
            .assertValue(expected)

        verify { service.getLanguages() }
    }
}