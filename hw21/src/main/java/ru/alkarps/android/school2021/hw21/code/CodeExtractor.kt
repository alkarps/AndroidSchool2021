package ru.alkarps.android.school2021.hw21.code

/**
 * Интерфейс компонента извлечения кода из текста
 */
interface CodeExtractor {
    fun getCode(rawText: String):String?
}