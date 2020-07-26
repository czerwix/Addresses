package com.mobeedev.employees.domain.usecase

import com.mobeedev.commonDomain.entity.Address
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.usecase.UseCase
import com.mobeedev.employees.domain.repository.EmployeeRepository


class SaveAddressUseCase(private val employeeRepository: EmployeeRepository) :
    UseCase<Address, SaveAddressUseCase.Params>() {

    override suspend fun run(params: Params) = employeeRepository.saveAddress(params.address)

    data class Params(val address: Address)
}