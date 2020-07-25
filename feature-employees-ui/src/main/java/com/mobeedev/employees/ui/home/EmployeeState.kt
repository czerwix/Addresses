package com.mobeedev.employees.ui.home

import com.airbnb.mvrx.MvRxState
import com.mobeedev.commonDomain.entity.Employee

data class EmployeeState(
    val employees: List<Employee> = emptyList()
) : MvRxState