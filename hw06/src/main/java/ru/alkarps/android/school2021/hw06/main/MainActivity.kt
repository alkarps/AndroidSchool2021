package ru.alkarps.android.school2021.hw06.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convertersView = findViewById<RecyclerView>(R.id.converters_list)
    }
}