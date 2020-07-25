package com.mobeedev.employees.domain.repository

import com.mobeedev.commonDomain.Result
import com.mobeedev.commonDomain.entity.Employee

interface EmployeeRepository {
    suspend fun saveEmployee(employee: Employee): Result<Employee>

    suspend fun editEmployee(employee: Employee): Result<Employee>

    suspend fun removeEmployee(employeeId: Long): Result<Unit>

    suspend fun getAllEmployees(): Result<List<Employee>>

    suspend fun getEmployee(employeeId: Long): Result<Employee>
}