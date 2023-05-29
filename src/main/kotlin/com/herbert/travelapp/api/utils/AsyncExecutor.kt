package com.herbert.travelapp.api.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AsyncExecutor {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    fun <P> execute(fn: () -> P) {
        scope.launch {
            fn()
            cleanUp()
        }
    }

    private suspend fun cleanUp() {
        job.join() // wait until all children jobs have completed
        job.cancel() // then cancel the parent job
    }
}