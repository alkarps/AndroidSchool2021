package ru.alkarps.android.school2021.hw21.code

import org.junit.Assert
import org.junit.Test

class RegexCodeExtractorTest {

    private val extractor = RegexCodeExtractor()

    @Test
    fun getCode_whenHasNumber_thenReturnNumber() {
        val expect = "123"
        val string = "aeae $expect sdawd"
        Assert.assertEquals(expect, extractor.getCode(string))
    }

    @Test
    fun getCode_whenNotHasNumber_thenReturnNull() {
        val string = "aeae sdawd"
        Assert.assertNull(extractor.getCode(string))
    }

    @Test
    fun getCode_whenNotMoreOneNumber_thenReturnFirstNumber() {
        val expect = "123"
        val string = "aeae $expect sdawd 321"
        Assert.assertEquals(expect, extractor.getCode(string))
    }
}