package ru.alkarps.android.school2021.hw18.presentation.activity.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw18.databinding.EventCreateActivityBinding

class EventCreateActivity : AppCompatActivity() {
    private lateinit var binding: EventCreateActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventCreateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}