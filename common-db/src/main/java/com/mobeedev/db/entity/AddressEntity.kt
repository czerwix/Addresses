package com.mobeedev.db.entity

import com.mobeedev.db.AddressDB

data class AddressEntity(
    val id: Long? = null,
    val address: String
)

fun AddressDB.toEntity() = AddressEntity(id = addressId, address = addressLine)