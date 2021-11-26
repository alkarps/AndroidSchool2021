package ru.alkarps.android.school2021.hw18.presentation.activity.event

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.EventCreateActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventCreateViewModel
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.util.asString
import ru.alkarps.android.school2021.hw18.util.toStringTime
import java.util.*

class EventCreateActivity : AppCompatActivity() {
    private lateinit var binding: EventCreateActivityBinding
    private lateinit var viewModel: EventCreateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventCreateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initDateInput()
        initTimeInput()
        initButton()
        supportActionBar?.title = getString(R.string.event_create_title)
    }

    private fun initViewModel() {
        val factory = (applicationContext as HolidayApiApplication)
            .holidayMain(this)
            .eventCreateViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(EventCreateViewModel::class.java)
        viewModel.success.observe(this, { finish() })
        viewModel.errorMessages.observe(this) {
            Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }

    private fun initDateInput() {
        binding.eventCreateDateInput.inputType = InputType.TYPE_NULL
        binding.eventCreateDateInput.keyListener = null
        binding.eventCreateDateInput.setOnClickListener { showDataPicker() }
        binding.eventCreateDateInput.setOnFocusChangeListener { _, f -> if (f) showDataPicker() }
    }

    private fun showDataPicker() {
        val dataPicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Дата события")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        dataPicker.addOnPositiveButtonClickListener {
            val cal = Calendar.getInstance().apply { timeInMillis = it }
            binding.eventCreateDateInput.setText(cal.asString())
        }
        dataPicker.show(supportFragmentManager, dataPicker.toString())
    }

    private fun initTimeInput() {
        binding.eventCreateTimeInput.inputType = InputType.TYPE_NULL
        binding.eventCreateTimeInput.keyListener = null
        binding.eventCreateTimeInput.setOnClickListener { showTimePicker() }
        binding.eventCreateTimeInput.setOnFocusChangeListener { _, f -> if (f) showTimePicker() }
    }

    private fun showTimePicker() {
        val dataPicker = MaterialTimePicker.Builder()
            .setTitleText("Время начала события")
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        dataPicker.addOnPositiveButtonClickListener {
            binding.eventCreateTimeInput.setText("${dataPicker.hour.toStringTime()}:${dataPicker.minute.toStringTime()}")
        }
        dataPicker.show(supportFragmentManager, dataPicker.toString())
    }

    private fun initButton() {
        binding.eventCreateButton.setOnClickListener {
            if (inputsIsValid()) {
                viewModel.create(
                    EventView(
                        binding.eventCreateNameInput.text.toString(),
                        binding.eventCreateDateInput.text.toString(),
                        binding.eventCreateTimeInput.text.toString(),
                        binding.eventCreateLocationInput.text?.toString()
                    )
                )
            }
        }
    }

    private fun inputsIsValid(): Boolean {
        val validName = validateField(binding.eventCreateNameInput)
        val validDate = validateField(binding.eventCreateDateInput)
        val validTime = validateField(binding.eventCreateTimeInput)
        return validDate && validName && validTime
    }

    private fun validateField(filed: TextInputEditText): Boolean {
        val invalid = filed.text?.toString().isNullOrBlank()
        if (invalid) filed.error = "Обязательное поле."
        return !invalid
    }
}