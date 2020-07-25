package com.mobeedev.employees.ui.home

import com.fieldcode.commonUi.mvrx.KoinMvRxViewModelFactory
import com.fieldcode.commonUi.mvrx.MvRxViewModel
import com.mobeedev.employees.domain.usecase.*

class EmployeeViewModel(
    state: EmployeeState,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val removeEmployeeUseCase: RemoveEmployeeUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase
):MvRxViewModel<EmployeeState>(state){

    init {
        // TODO: 25/07/2020 get all employees
    }

    companion object:KoinMvRxViewModelFactory<EmployeeViewModel,EmployeeState>(
        EmployeeViewModel::class
    )
}