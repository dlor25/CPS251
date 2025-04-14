package com.example.localboundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log

class StopwatchService : Service() {

    private val binder = LocalBinder()
    private val handler = Handler(Looper.getMainLooper())
    private var startTime = 0L
    private var elapsedTime = 0L
    private var running = false

    inner class LocalBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running) {
                val timeNow = SystemClock.elapsedRealtime()
                elapsedTime = timeNow - startTime
                Log.d("StopwatchService", "Elapsed Time: $elapsedTime ms")
                handler.postDelayed(this, 100)  // Update every 100ms
            }
        }
    }

    fun startTimer() {
        if (!running) {
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            Log.d("StopwatchService", "Timer started at: $startTime ms")
            handler.post(updateRunnable)
            running = true
        }
    }

    fun pauseTimer() {
        running = false
        handler.removeCallbacks(updateRunnable)
        Log.d("StopwatchService", "Timer paused at: $elapsedTime ms")
    }

    fun stopTimer() {
        running = false
        handler.removeCallbacks(updateRunnable)
        elapsedTime = 0L
        Log.d("StopwatchService", "Timer stopped and reset")
    }

    fun getElapsedTime(): Long {
        return elapsedTime
    }
}
