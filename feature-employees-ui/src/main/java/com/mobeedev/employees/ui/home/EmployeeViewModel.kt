package com.mobeedev.employees.ui.home

import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonUi.mvrx.KoinMvRxViewModelFactory
import com.mobeedev.commonUi.mvrx.MvRxViewModel
import com.mobeedev.employees.domain.usecase.GetEmployeesUseCase
import com.mobeedev.employees.domain.usecase.RemoveEmployeeUseCase
import com.mobeedev.employees.domain.usecase.SearchByNameUseCase
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.entity.toItemList

class EmployeeViewModel(
    state: EmployeeState,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val removeEmployeeUseCase: RemoveEmployeeUseCase,
    private val searchByNameUseCase: SearchByNameUseCase
) : MvRxViewModel<EmployeeState>(state) {

    fun init() {
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

    fun searchEmployee(search: String) {
        if (search.isBlank()) {
            getEmployeesUseCase.execute(
                mapper = List<Employee>::toItemList,
                stateReducer = {
                    copy(employees = it() ?: employees)
                }
            )
        } else {
            searchByNameUseCase.execute(
                params = SearchByNameUseCase.Params(search),
                mapper = List<Employee>::toItemList,
                stateReducer = { copy(employees = it() ?: employees) }
            )
        }
    }

    companion object : KoinMvRxViewModelFactory<EmployeeViewModel, EmployeeState>(
        EmployeeViewModel::class
    )
}