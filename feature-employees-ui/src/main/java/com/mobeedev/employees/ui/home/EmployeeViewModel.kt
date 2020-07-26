package com.mobeedev.employees.ui.home

import com.fieldcode.commonUi.mvrx.KoinMvRxViewModelFactory
import com.fieldcode.commonUi.mvrx.MvRxViewModel
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.employees.domain.usecase.*
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.entity.toItemList

class EmployeeViewModel(
    state: EmployeeState,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val removeEmployeeUseCase: RemoveEmployeeUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase
) : MvRxViewModel<EmployeeState>(state) {

    init {
        getEmployeesUseCase.execute(
            mapper = List<Employee>::toItemList,
            stateReducer = {
                copy(employees = it() ?: employees)
            }
        )
    }

    fun removeEmployee(employee: EmployeeItem) {
        removeEmployeeUseCase.execute(params = RemoveEmployeeUseCase.Params(employee.id!!)) {
            val newEmployees = employees.toMutableList()
            newEmployees.remove(employee)
            copy(employees = newEmployees)
        }
    }

    companion object : KoinMvRxViewModelFactory<EmployeeViewModel, EmployeeState>(
        EmployeeViewModel::class
    )
}