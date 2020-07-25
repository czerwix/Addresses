package com.mobeedev.employees.ui.home

import androidx.fragment.app.activityViewModels
import com.airbnb.mvrx.activityViewModel
import com.mobeedev.commonUi.BaseFragment

class EmployeeHomeFragment :BaseFragment(){

    private val employeeViewModel:EmployeeViewModel by activityViewModel()

//    private val employeeController = EmployeeEpoxyController()
    // TODO: 25/07/2020 create epoxy controller or use simple controller

    override fun invalidate() {
        // TODO: 25/07/2020 bind epoxy
    }

}