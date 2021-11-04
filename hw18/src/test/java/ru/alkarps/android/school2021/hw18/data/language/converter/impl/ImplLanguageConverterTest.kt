package ru.alkarps.android.school2021.hw18.data.language.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageConverterTest {
    @Test
    fun fromDto() {
        val code = "Code"
        val name = "Name"
        val dto = LanguageDTO(code, name)
        assertThat(ImplLanguageConverter().fromDto(listOf(dto))).isNotNull
            .isNotEmpty
            .hasSize(1)
            .isEqualTo(listOf(Language(code, name)))
    }
}