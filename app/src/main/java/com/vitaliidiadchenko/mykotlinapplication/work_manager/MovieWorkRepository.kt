package com.vitaliidiadchenko.mykotlinapplication.work_manager

import androidx.work.*
import java.util.concurrent.TimeUnit

class MovieWorkRepository {
    val constr = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .build()

    val periodicUploadWork = PeriodicWorkRequest
        .Builder(MovieWorker::class.java, 8, TimeUnit.HOURS)
        .setConstraints(constr).build()

    companion object {
        const val MOVIES_UPDATE = "Movies_update"
    }
}