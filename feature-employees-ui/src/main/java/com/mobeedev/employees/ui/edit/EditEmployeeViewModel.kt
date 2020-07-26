package com.mobeedev.employees.ui.edit

import com.fieldcode.commonUi.mvrx.KoinMvRxViewModelFactory
import com.fieldcode.commonUi.mvrx.MvRxViewModel
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.employees.domain.usecase.GetEmployeeUseCase
import com.mobeedev.employees.domain.usecase.SaveEmployeeUseCase
import com.mobeedev.employees.domain.usecase.UpdateEmployeeUseCase
import com.mobeedev.employees.entity.AddressItem
import com.mobeedev.employees.entity.toItem
import org.threeten.bp.ZonedDateTime
import kotlin.random.Random

class EditEmployeeViewModel(
    state: EditEmployeeState,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase
) : MvRxViewModel<EditEmployeeState>(state) {
    fun getEmployee(id: Long) {
        getEmployeeUseCase.execute(
            params = GetEmployeeUseCase.Params(id),
            mapper = Employee::toItem,
            stateReducer = { copy(employee = it()) }
        )
    }

    fun onAddressRemoved(address: AddressItem?) = withState { state ->
        val newAddresses = state.addresses.toMutableList()
        newAddresses.remove(address)
        setState {
            copy(addresses = newAddresses)
        }
    }

    fun onAddressAdded(address: String?) {
        if (address == null) return
        withState { state ->
            val newAddress = state.addresses.toMutableList()
            newAddress.add(AddressItem(Random.nextLong(), address))
            setState { copy(addresses = newAddress) }
        }
    }

    fun onBirthDateChanged(birthDate: ZonedDateTime) {

    }

    companion object : KoinMvRxViewModelFactory<EditEmployeeViewModel, EditEmployeeState>(
        EditEmployeeViewModel::class
    )
}