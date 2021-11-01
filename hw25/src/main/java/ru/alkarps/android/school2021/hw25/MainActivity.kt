package ru.alkarps.android.school2021.hw25

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw25.ForegroundService.Companion.START_INTENT
import ru.alkarps.android.school2021.hw25.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val serviceConnection = ForegroundServiceConnection()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startService.setOnClickListener {
            serviceConnection.service?.let {
                if (!it.onForeground) {
                    val intent = serviceIntent().apply { action = START_INTENT }
                    startService(intent)
                }
            }
        }
    }

    private fun serviceIntent() = Intent(this, ForegroundService::class.java)

    override fun onResume() {
        super.onResume()
        bindService(serviceIntent(), serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        unbindService(serviceConnection)
    }

    class ForegroundServiceConnection : ServiceConnection {
        var service: ForegroundService? = null
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            this.service = (service as ForegroundService.LocalBinder).getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            this.service = null
        }
    }
}