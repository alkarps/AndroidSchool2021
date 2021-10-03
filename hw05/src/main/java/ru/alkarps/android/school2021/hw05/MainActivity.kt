package ru.alkarps.android.school2021.hw05

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw05.holder.BasketListener
import ru.alkarps.android.school2021.hw05.model.BasketItem

class MainActivity : AppCompatActivity(), BasketListener {
    private lateinit var repository: BasketRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val basketAdapter = BasketAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.baskets)
        recyclerView.adapter = basketAdapter
        repository = BasketRepository { basketAdapter.submitList(it) }

        findViewById<Button>(R.id.add_basket).setOnClickListener { repository.addBasket() }
        findViewById<Button>(R.id.remove_all_baskets).setOnClickListener { repository.removeAllBaskets() }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(DATA_KEY)) {
            repository.restore(savedInstanceState.getParcelableArrayList(DATA_KEY)!!)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(DATA_KEY, repository.getData())
    }

    private fun showAlarm() {
        val message = "Жадность наносит вред не только репутации человека, но и его здоровью!"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun addApple(basket: BasketItem) {
        if (!repository.addApple(basket)) {
            showAlarm()
        }
    }

    override fun removeApple(apple: BasketItem) {
        repository.removeApple(apple)
    }

    companion object {
        const val DATA_KEY = "BasketsKey"
    }
}