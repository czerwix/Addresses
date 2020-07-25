package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.UseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository

class GetEmployeeUseCase(private val employeeRepository: EmployeeRepository) :
    UseCase<Employee, GetEmployeeUseCase.Params>() {

    override suspend fun run(params: Params) = employeeRepository.getEmployee(params.employeeId)

    data class Params(val employeeId: Long)
}