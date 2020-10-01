package com.example.android.marsrealestate

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CountingWorker(context: Context, workerParams: WorkerParameters)
        : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("TAG", "doWork: working")

        return Result.success()
    }
}