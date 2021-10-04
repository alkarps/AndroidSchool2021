package ru.alkarps.android.school2021.hw06.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.Const
import ru.alkarps.android.school2021.hw06.model.Quantity

class ConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.converter_activity)

        val quantity = intent.getParcelableExtra<Quantity>(Const.QUANTITY_INTENT_KEY)!!

        val converterView = findViewById<RecyclerView>(R.id.converter)
        converterView.adapter = ConverterAdapter(quantity) {diffResult, converterAdapter ->
            converterView.post {
                diffResult.dispatchUpdatesTo(converterAdapter)
            }
        }
    }
}