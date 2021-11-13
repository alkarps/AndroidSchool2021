package ru.alkarps.android.school2021.hw29.presentation.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw29.databinding.AddDictionaryItemBinding
import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem
import ru.alkarps.android.school2021.hw29.presentation.dictionary.DictionaryViewModel
import ru.alkarps.android.school2021.hw29.presentation.dictionary.factory.DictionaryViewModelFactory

class AddActivity : AppCompatActivity() {
    private lateinit var binding: AddDictionaryItemBinding
    private lateinit var viewModel: DictionaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDictionaryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DictionaryViewModelFactory(applicationContext))
            .get(DictionaryViewModel::class.java)
        viewModel.finishing.observe(this) { finish() }

        binding.addButton.setOnClickListener { insertNewWord() }
    }

    private fun insertNewWord() {
        val keyword = binding.keyword.text.toString()
        val translation = binding.translation.text.toString()
        if (keyword.isNotBlank() && translation.isNotBlank()) {
            val di = DictionaryItem(keyword, translation)
            viewModel.insertWord(di)
            binding.addButton.isEnabled = false
        } else {
            Toast.makeText(this, "Keyword or transaction is blank", Toast.LENGTH_LONG).show()
        }
    }
}