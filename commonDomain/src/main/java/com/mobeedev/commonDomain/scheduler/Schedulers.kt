package com.mobeedev.commonDomain.scheduler

import kotlinx.coroutines.CoroutineDispatcher

interface Schedulers {
    fun io(): CoroutineDispatcher
    fun background(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
