package ru.alkarps.android.school2021.hw18.data.language

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.language.api.LanguageApi
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageClientTest {

    @Test
    fun getLanguages() {
        val languages = listOf(LanguageDTO("codeDTO", "nameDTO"))
        val expected = listOf(Language("code", "name"))
        val api = mockk<LanguageApi>()
        every { api.getLanguages() } returns languages
        val converter = mockk<LanguageConverter>()
        every { converter.fromDto(any()) } returns expected

        assertThat(ImplLanguageClient(api, converter).getLanguages()).isEqualTo(expected)

        verify { api.getLanguages() }
        verify { converter.fromDto(languages) }
    }
}