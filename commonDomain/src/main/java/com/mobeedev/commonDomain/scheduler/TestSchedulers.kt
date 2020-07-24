package com.mobeedev.commonDomain.scheduler

import com.mobeedev.commonDomain.scheduler.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestSchedulers : Schedulers {
    override fun io(): CoroutineDispatcher = Dispatchers.Unconfined
    override fun background(): CoroutineDispatcher = Dispatchers.Unconfined
    override fun main(): CoroutineDispatcher = Dispatchers.Unconfined
}
