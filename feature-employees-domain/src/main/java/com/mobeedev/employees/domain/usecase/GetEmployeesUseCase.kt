package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.Result
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.NoParametersUseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository

class GetEmployeesUseCase(private val employeeRepository: EmployeeRepository) :
    NoParametersUseCase<List<Employee>>() {

    override suspend fun run() = employeeRepository.getAllEmployees()
}