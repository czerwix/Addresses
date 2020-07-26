package com.mobeedev.employees.entity

import com.mobeedev.commonDomain.entity.Address
import com.mobeedev.commonDomain.entity.Employee

data class AddressItem(
    val id: Long? = null,
    val address: String
)

fun Address.toItem() = AddressItem(
    id = id,
    address = address
)