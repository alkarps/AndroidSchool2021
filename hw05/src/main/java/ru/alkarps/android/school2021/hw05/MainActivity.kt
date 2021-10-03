package ru.alkarps.android.school2021.hw05

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var repository: BasketRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val basketAdapter = BasketAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.baskets)
        recyclerView.adapter = basketAdapter
        repository = BasketRepository { basketAdapter.submitList(it) }

        findViewById<Button>(R.id.add_basket).setOnClickListener { repository.addBasket() }
        findViewById<Button>(R.id.remove_all_baskets).setOnClickListener { repository.removeAllBaskets() }
    }

    private fun showAlarm(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}