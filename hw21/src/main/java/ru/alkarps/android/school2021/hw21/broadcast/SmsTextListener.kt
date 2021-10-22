package ru.alkarps.android.school2021.hw21.broadcast

/**
 * Листенер на получение текста из СМС
 */
interface SmsTextListener {
    fun onGettingSmsText(text: String)
}