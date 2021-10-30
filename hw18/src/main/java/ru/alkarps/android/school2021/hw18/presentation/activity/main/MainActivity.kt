package ru.alkarps.android.school2021.hw18.presentation.activity.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.ActivityMainBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter.DayWithHolidaysAdapter
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)?.apply {
            divider.setDrawable(this)
        }
        binding.recyclerView.addItemDecoration(divider)

        viewModel.progressLiveData.observe(this) {
            binding.progressFrameLayout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.errorLiveData.observe(this) {
            Snackbar.make(binding.root, it.toString(), BaseTransientBottomBar.LENGTH_LONG).show()
        }
        viewModel.holidaysLiveData.observe(this) {
            binding.recyclerView.adapter = DayWithHolidaysAdapter(it)
        }
        if (savedInstanceState == null) {
            viewModel.loadHolidays()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}