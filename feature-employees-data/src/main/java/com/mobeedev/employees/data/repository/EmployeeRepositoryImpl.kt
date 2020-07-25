package com.mobeedev.employees.data.repository

import com.mobeedev.commonDomain.Result
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.map
import com.mobeedev.commonDomain.safeCall
import com.mobeedev.db.entity.EmployeeEntity
import com.mobeedev.db.entity.toDomain
import com.mobeedev.db.entity.toEntity
import com.mobeedev.employees.data.datasource.local.EmployeeLocalDataSource
import com.mobeedev.employees.domain.repository.EmployeeRepository

class EmployeeRepositoryImpl(
    private val employeeLocalDataSource: EmployeeLocalDataSource
) : EmployeeRepository { // TODO: 25/07/2020 implement interface from domain

    override suspend fun saveEmployee(employee: Employee): Result<Employee> =
        safeCall { employeeLocalDataSource.saveEmployee(employee.toEntity()) }
            .map { it.toDomain() }

    override suspend fun editEmployee(employee: Employee): Result<Employee> =
        safeCall { employeeLocalDataSource.editEmployee(employee.toEntity()) }
            .map { it.toDomain() }

    override suspend fun removeEmployee(employeeId: Long): Result<Unit> =
        safeCall { employeeLocalDataSource.removeEmployee(employeeId) }.map { Unit }

    override suspend fun getAllEmployees(): Result<List<Employee>> =
        safeCall { employeeLocalDataSource.getAllEmployees() }
            .map { it.map(EmployeeEntity::toDomain) }

    override suspend fun getEmployee(employeeId: Long): Result<Employee> = safeCall {
        employeeLocalDataSource.getEmployee(employeeId).toDomain()
    }

}