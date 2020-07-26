package com.mobeedev.employees.entity

import androidx.lifecycle.Transformations.map
import com.mobeedev.commonDomain.entity.Employee
import com.mobeedev.commonDomain.entity.Gender
import org.threeten.bp.ZonedDateTime

data class EmployeeItem(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: ZonedDateTime,
    val gender: Gender,
    val addresses: List<AddressItem>
)

fun Employee.toItem() = EmployeeItem(
    id =id,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    gender = gender,
    addresses = addresses.map{it.toItem()})

fun List<Employee>.toItemList() = map(Employee::toItem)
