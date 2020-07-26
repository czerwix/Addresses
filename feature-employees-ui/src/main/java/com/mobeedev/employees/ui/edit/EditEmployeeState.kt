package com.mobeedev.employees.ui.edit

import com.airbnb.mvrx.MvRxState
import com.mobeedev.employees.entity.AddressItem
import com.mobeedev.employees.entity.EmployeeItem
import org.threeten.bp.ZonedDateTime

data class EditEmployeeState(
    val employee: EmployeeItem? = null,
    val addresses: List<AddressItem> = emptyList(),
    val birthDate: ZonedDateTime = ZonedDateTime.now()
) : MvRxState