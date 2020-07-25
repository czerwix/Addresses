package com.mobeedev.db.entity

import com.mobeedev.commonDomain.entity.Address
import com.mobeedev.db.AddressDB

data class AddressEntity(
    val id: Long? = null,
    val address: String
)

fun AddressDB.toEntity() = AddressEntity(id = addressId, address = addressLine)

fun AddressEntity.toDomain() = Address(id = id, address = address)

fun Address.toEntity() = AddressEntity(id = id, address = address)