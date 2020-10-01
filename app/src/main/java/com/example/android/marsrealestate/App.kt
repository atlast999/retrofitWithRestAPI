package com.example.android.marsrealestate

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val workRequest = PeriodicWorkRequest.Builder(CountingWorker::class.java, 5, TimeUnit.SECONDS)
                .addTag("logging")
                .build()
        WorkManager.getInstance(this).enqueue(workRequest)

    }
}