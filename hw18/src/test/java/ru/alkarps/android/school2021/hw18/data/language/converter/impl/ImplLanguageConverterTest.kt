package ru.alkarps.android.school2021.hw18.data.language.converter.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageConverterTest {
    private val code = "Code"
    private val name = "Name"
    private val language = Language(code, name)
    private val languageDTO = LanguageDTO(code, name)
    private val languageEntity = LanguageEntity(code, name)

    @Test
    fun fromDto() {
        assertThat(ImplLanguageConverter().fromDto(listOf(languageDTO))).isNotNull
            .isNotEmpty
            .hasSize(1)
            .isEqualTo(listOf(language))
    }

    @Test
    fun fromEntity_whenIsNotList() {
        assertThat(ImplLanguageConverter().fromEntity(languageEntity)).isNotNull
            .isEqualTo(language)
    }

    @Test
    fun toEntity() {
        assertThat(ImplLanguageConverter().toEntity(listOf(language))).isNotNull
            .isNotEmpty
            .hasSize(1)
            .isEqualTo(listOf(languageEntity.copy(code= code.lowercase())))
    }
}