package ru.alkarps.android.school2021.hw03

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class TranslateServiceTest(
    private val input: String,
    private val expected: String
) {

    @Test
    fun translate() {
        Assert.assertEquals(expected, TranslateService.translate(input))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<*>> {
            return listOf(
                arrayOf("", ""),
                arrayOf(" ", " "),
                arrayOf(" А", " A"),
                arrayOf(" а", " a")
            )
        }
    }
}