package ru.alkarps.android.school2021.hw29.presentation.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw29.databinding.ActivityMainBinding
import ru.alkarps.android.school2021.hw29.presentation.main.view.adapter.DictionaryAdapter
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
        viewModel.insertData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadDataRx()
        viewModel.dictionaryItems.observe(this) {
            binding.words.adapter = DictionaryAdapter(it)
        }
        viewModel.errors.observe(this) {
            Snackbar.make(binding.root, it.toString(), BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }
}