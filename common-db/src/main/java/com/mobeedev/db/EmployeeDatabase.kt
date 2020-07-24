package com.mobeedev.db

interface EmployeeDatabase {
    fun  addressQueries(): AddressDBQueries
    fun  employeeQueries(): EmployeesDBQueries
}