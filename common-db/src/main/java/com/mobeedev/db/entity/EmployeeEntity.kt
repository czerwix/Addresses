package com.mobeedev.db.entity

import com.mobeedev.db.EmployeeDB
import org.threeten.bp.ZonedDateTime

data class EmployeeEntity(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: ZonedDateTime,
    val gender: Gender,
    val addresses: List<AddressEntity>
)

fun EmployeeDB.toEntity(addresses: List<AddressEntity>) = EmployeeEntity(
    id = employeeId,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    gender = Gender.valueOf(gender),
    addresses = addresses
)