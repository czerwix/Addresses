package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.UseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository


class SaveEmployeeUseCase(private val employeeRepository: EmployeeRepository) :
    UseCase<Employee, SaveEmployeeUseCase.Params>() {

    override suspend fun run(params: Params) = employeeRepository.saveEmployee(params.employee)

    data class Params(val employee: Employee)
}