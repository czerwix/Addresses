package com.mobeedev.commonDomain.error

sealed class EmployeeError : EmployeeException() {
    data class Unknown(override val originalException: Throwable? = null) : EmployeeError()
}
