package com.mobeedev.employees

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmployeesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() =
        startKoin {
            androidContext(this@EmployeesApplication)
            androidLogger()
            //todo load base koin modules when defined [SK]
        }

}