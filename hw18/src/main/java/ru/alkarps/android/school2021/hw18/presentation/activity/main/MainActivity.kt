package ru.alkarps.android.school2021.hw18.presentation.activity.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.MainActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter.DayWithHolidaysAdapter
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel

/**
 * Активити главного экрана
 *
 * @constructor Новый экземпляр активити главного экрана
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)?.apply {
            divider.setDrawable(this)
        }
        binding.recyclerView.addItemDecoration(divider)

        initViewModel()

        if (savedInstanceState == null) {
            viewModel.loadHolidays()
        }
    }

    private fun initViewModel() {
        val factory = (applicationContext as HolidayApiApplication).holidayMain.viewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.progressLiveData.observe(this) {
            binding.progressFrameLayout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.errorLiveData.observe(this) {
            Snackbar.make(binding.root, it.toString(), BaseTransientBottomBar.LENGTH_LONG).show()
        }
        viewModel.holidaysLiveData.observe(this) {
            binding.recyclerView.adapter = DayWithHolidaysAdapter(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.setting_menu) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}