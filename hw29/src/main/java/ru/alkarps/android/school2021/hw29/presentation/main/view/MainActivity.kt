package ru.alkarps.android.school2021.hw29.presentation.main.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw29.databinding.ActivityMainBinding
import ru.alkarps.android.school2021.hw29.presentation.main.view.model.DictionaryViewModel
import ru.alkarps.android.school2021.hw29.presentation.main.view.model.factory.DictionaryViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DictionaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DictionaryViewModelFactory(applicationContext))
            .get(DictionaryViewModel::class.java)
//        viewModel.insertData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadDataRx()
        viewModel.dictionaryItems.observe(this) {
            binding.words.text = TextUtils.join(",", it)
        }
        viewModel.errors.observe(this) {
            Log.i("MainActivity", "Error", it)
        }
    }
}