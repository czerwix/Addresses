package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.UseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository

class SearchByNameUseCase(private val employeeRepository: EmployeeRepository) :
    UseCase<List<Employee>, SearchByNameUseCase.Params>() {

    override suspend fun run(params: Params) = employeeRepository.searchByName(params.search)

    data class Params(val search: String)
}