package com.mobeedev.employees

import com.mobeedev.db.EmployeeDatabase
import com.mobeedev.db.MyDatabase

class EmployeeDatabaseImpl(
    private val database: Lazy<MyDatabase>
) : EmployeeDatabase {

    private val myDatabase by lazy { database.value }

    override fun addressQueries() = myDatabase.addressDBQueries

    override fun employeeQueries() = myDatabase.employeeDBQueries

}