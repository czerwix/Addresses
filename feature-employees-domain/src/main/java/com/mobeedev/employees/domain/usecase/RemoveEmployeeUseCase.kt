package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.UseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository

class RemoveEmployeeUseCase(private val employeeRepository: EmployeeRepository) :
    UseCase<Unit, RemoveEmployeeUseCase.Params>() {

    override suspend fun run(params: Params) = employeeRepository.removeEmployee(params.employeeId)

    data class Params(val employeeId: Long)
}