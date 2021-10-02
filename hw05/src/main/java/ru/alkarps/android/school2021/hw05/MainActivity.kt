package ru.alkarps.android.school2021.hw05

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val basketAdapter = BasketAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.baskets)
        recyclerView.adapter = basketAdapter

        findViewById<Button>(R.id.add_basket).setOnClickListener { basketAdapter.addBasket() }
        findViewById<Button>(R.id.remove_all_baskets).setOnClickListener { basketAdapter.removeAllBaskets() }
    }
}