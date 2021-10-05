package ru.alkarps.android.school2021.hw14

interface OperationApi {
    fun addTransaction(addOperation: Boolean, needAddToBackstack: Boolean)
    fun removeTransaction()
}