package com.mobeedev.employees.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.ui.home.view.addressView
import com.mobeedev.employees.ui.home.view.employeeView

class EmployeeEpoxyController(
    private val onRemoveLongClicked: (EmployeeItem) -> Unit,
    private val onEditClicked: (EmployeeItem) -> Unit
) : TypedEpoxyController<List<EmployeeItem>>() {

    override fun buildModels(data: List<EmployeeItem>) {
        if (data.isEmpty()) return

        data.forEach {
            employeeView {
                id(it.id)
                onRemoveClicked(onRemoveLongClicked)
                onEditClicked(onEditClicked)
                employeeItem(it)

                it.addresses.forEach {
                    addressView {
                        id(it.id)
                        addressItem(it)
                    }
                }
            }
        }
    }
}