package com.example.localboundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.localboundservice.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var stopwatchService: StopwatchService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as StopwatchService.LocalBinder
            stopwatchService = binder.getService()
            isBound = true
            Log.d("MainActivity", "Service connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            Log.d("MainActivity", "Service disconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, StopwatchService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        binding.startButton.setOnClickListener {
            if (isBound) {
                stopwatchService?.startTimer()
                Log.d("MainActivity", "Start button clicked")
            }
        }

        binding.pauseButton.setOnClickListener {
            if (isBound) {
                stopwatchService?.pauseTimer()
                Log.d("MainActivity", "Pause button clicked")
            }
        }

        binding.stopButton.setOnClickListener {
            if (isBound) {
                stopwatchService?.stopTimer()
                Log.d("MainActivity", "Stop button clicked")
            }
        }

        updateElapsedTime()
    }

    private fun updateElapsedTime() {
        binding.timeTextView.postDelayed({
            if (isBound && stopwatchService != null) {
                val elapsedTime = stopwatchService?.getElapsedTime() ?: 0L
                val seconds = (elapsedTime / 1000) % 60
                val minutes = (elapsedTime / (1000 * 60)) % 60
                val hours = (elapsedTime / (1000 * 60 * 60)) % 24
                binding.timeTextView.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
            }
            updateElapsedTime()
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
            Log.d("MainActivity", "Service unbound")
        }
    }
}
