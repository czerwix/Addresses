package com.mobeedev.commonDomain.entity

import com.mobeedev.commonDomain.entity.Gender
import org.threeten.bp.ZonedDateTime

data class Employee(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: ZonedDateTime,
    val gender: Gender,
    val addresses: List<Address>
)
