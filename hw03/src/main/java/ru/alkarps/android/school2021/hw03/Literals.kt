package ru.alkarps.android.school2021.hw03

data class Literals<T>(private val chars: List<T>) {
    fun hasLiteral(literal: T) = chars.contains(literal)

    fun getIndex(literal: T) = chars.indexOf(literal)

    fun getLiteral(index: Int) = chars[index]
}
