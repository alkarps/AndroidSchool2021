package ru.alkarps.android.school2021.hw21.code

class RegexCodeExtractor : CodeExtractor {
    private val regex = Regex("\\d+")
    override fun getCode(rawText: String): String? = regex.find(rawText)?.value
}