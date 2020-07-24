package com.mobeedev.commonDomain.error

abstract class EmployeeException : Exception() {
    abstract val originalException: Throwable?
}
