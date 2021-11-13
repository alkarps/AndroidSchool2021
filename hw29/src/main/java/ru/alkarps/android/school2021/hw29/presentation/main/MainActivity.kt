package ru.alkarps.android.school2021.hw29.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw29.databinding.ActivityMainBinding
import ru.alkarps.android.school2021.hw29.presentation.add.AddActivity
import ru.alkarps.android.school2021.hw29.presentation.dictionary.DictionaryViewModel
import ru.alkarps.android.school2021.hw29.presentation.dictionary.factory.DictionaryViewModelFactory
import ru.alkarps.android.school2021.hw29.presentation.main.adapter.DictionaryAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DictionaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DictionaryViewModelFactory(applicationContext))
            .get(DictionaryViewModel::class.java)

        binding.addNewItem.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, ADD_ACTIVITY_REQUEST_CODE)
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ADD_ACTIVITY_REQUEST_CODE) viewModel.loadDataRx()
        else super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        private const val ADD_ACTIVITY_REQUEST_CODE = 1
    }
}