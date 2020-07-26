package com.mobeedev.employees.ui.edit

import com.mobeedev.commonDomain.doOnSuccess
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.entity.Gender
import com.mobeedev.commonUi.mvrx.KoinMvRxViewModelFactory
import com.mobeedev.commonUi.mvrx.MvRxViewModel
import com.mobeedev.employees.domain.usecase.GetEmployeeUseCase
import com.mobeedev.employees.domain.usecase.SaveEmployeeUseCase
import com.mobeedev.employees.entity.AddressItem
import com.mobeedev.employees.entity.toDomain
import com.mobeedev.employees.entity.toItem
import org.threeten.bp.ZonedDateTime
import kotlin.random.Random

class EditEmployeeViewModel(
    state: EditEmployeeState,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase
) : MvRxViewModel<EditEmployeeState>(state) {
    fun getEmployee(id: Long) {
        getEmployeeUseCase.execute(
            params = GetEmployeeUseCase.Params(id),
            mapper = Employee::toItem,
            stateReducer = {
                val employee = it()
                copy(employee = employee, addresses = employee?.addresses?: emptyList())
            }
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
        setState { copy(birthDate = birthDate) }
    }

    fun addEmployee(firstName: String, lastName: String, gender: Gender) {
        withState { state ->
            saveEmployeeUseCase.invoke(
                parentJob = viewModelJob,
                params = SaveEmployeeUseCase.Params(
                    Employee(
                        firstName = firstName,
                        lastName = lastName,
                        gender = gender,
                        birthDate = state.birthDate,
                        addresses = state.addresses.map { it.toDomain() })
                )
            ) { result ->
                result.doOnSuccess {
                    setState { copy(navigationEventGoToHome = true) }
                }
            }
        }
    }

    companion object : KoinMvRxViewModelFactory<EditEmployeeViewModel, EditEmployeeState>(
        EditEmployeeViewModel::class
    )
}