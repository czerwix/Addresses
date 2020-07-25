package com.mobeedev.employees.data.repository

import com.mobeedev.commonDomain.safeCall
import com.mobeedev.db.entity.EmployeeEntity
import com.mobeedev.employees.data.datasource.local.EmployeeLocalDataSource

class EmployeeRepository(
    private val employeeLocalDataSource: EmployeeLocalDataSource
) { // TODO: 25/07/2020 implement interface from domain

    fun saveEmployee(employeeEntity: EmployeeEntity) =
        safeCall {
            employeeLocalDataSource.saveEmployee(employeeEntity)
        }

    fun editEmployee(employeeEntity: EmployeeEntity) =
        safeCall { employeeLocalDataSource.editEmployee(employeeEntity) }

    fun removeEmployee(employeeId: Long) =
        safeCall { employeeLocalDataSource.removeEmployee(employeeId) }

    fun getAllEmployees() = safeCall { employeeLocalDataSource.getAllEmployees() }

    fun getEmployee(employeeId: Long) = safeCall {
        fun getEmployee(employeeId: Long) = safeCall { }
    }

}