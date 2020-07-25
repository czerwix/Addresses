package com.mobeedev.employees.di

import android.preference.PreferenceManager
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mobeedev.db.EmployeeDB
import com.mobeedev.db.EmployeeDatabase
import com.mobeedev.db.MyDatabase
import com.mobeedev.db.util.zonedDateTimeAdapter
import com.mobeedev.employees.EmployeeDatabaseImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.property.inject

private const val DB_NAME = "EMPLOYEES_DATA"

val mainModule = module(override = true) {

    single {
        AndroidSqliteDriver(
            MyDatabase.Schema,
            context = androidContext(),
            name = "$DB_NAME.db",
            callback = object : AndroidSqliteDriver.Callback(MyDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.execSQL("PRAGMA foreign_keys=ON;")
                }
            }
        ) as SqlDriver
    }

    single {
        MyDatabase(
            driver = get(),
            EmployeeDBAdapter = EmployeeDB.Adapter(
                birthDateAdapter = zonedDateTimeAdapter
            )
        )
    }

    single {
        EmployeeDatabaseImpl(database = inject()) as EmployeeDatabase
    }

    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}
