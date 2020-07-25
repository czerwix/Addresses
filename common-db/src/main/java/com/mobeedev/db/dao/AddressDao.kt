package com.mobeedev.db.dao

import com.mobeedev.db.AddressDBQueries
import com.mobeedev.db.entity.AddressEntity
import com.mobeedev.db.entity.toEntity

class AddressDao(private val addressQueries: AddressDBQueries) {

    fun insertOrReplace(addressEntity: AddressEntity, employeeId: Long) = with(addressEntity) {
        addressQueries.insertOrReplace(
            addressLine = address,
            employeeId = employeeId
        )

        val addressId = addressQueries.rowId().executeAsOne()
        return@with addressEntity.copy(id = addressId)
    }

    fun selectByEmployee(employeeId: Long) =
        addressQueries.selectAddressByEmployee(employeeId).executeAsList().map { it.toEntity() }
}