package ru.alkarps.android.school2021.hw16.client

interface RestClient {
    fun doPost(): String
    fun doGet(): String
}