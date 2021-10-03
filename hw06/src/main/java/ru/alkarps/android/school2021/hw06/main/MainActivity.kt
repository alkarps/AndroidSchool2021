package ru.alkarps.android.school2021.hw06.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.converter.ConverterActivity
import ru.alkarps.android.school2021.hw06.model.Const
import ru.alkarps.android.school2021.hw06.model.Quantity
import ru.alkarps.android.school2021.hw06.repository.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = Repository()
        val convertersView = findViewById<RecyclerView>(R.id.converters_list)
        convertersView.adapter = ListAdapter(repository.quantities, this::startConverterActivity)
        convertersView.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2)
        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.converters_list_divider, theme)?.apply {
            divider.setDrawable(this)
        }
        convertersView.addItemDecoration(divider)
    }

    private fun startConverterActivity(quantity: Quantity) {
        val intent = Intent(this, ConverterActivity::class.java).apply {
            putExtra(Const.QUANTITY_INTENT_KEY, quantity.units)
        }
        startActivity(intent)
    }
}