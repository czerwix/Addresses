package com.mobeedev.employees.di

import android.preference.PreferenceManager
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mobeedev.db.MyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

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


    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}
