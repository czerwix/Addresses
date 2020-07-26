package com.mobeedev.employees

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mobeedev.db.DatabaseModules
import com.mobeedev.employees.di.EmployeeModules
import com.mobeedev.employees.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

open class EmployeesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        initializeKoin()
    }

    private fun initializeKoin() =
        startKoin {
            androidContext(this@EmployeesApp)
            androidLogger()

            loadKoinModules(listOf(mainModule))
            DatabaseModules.load()
            EmployeeModules.load()
        }

}