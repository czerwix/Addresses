package com.mobeedev.employees.ui.home

import com.airbnb.mvrx.MvRxState
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.employees.entity.EmployeeItem

data class EmployeeState(
    val employees: List<EmployeeItem> = emptyList()
) : MvRxState