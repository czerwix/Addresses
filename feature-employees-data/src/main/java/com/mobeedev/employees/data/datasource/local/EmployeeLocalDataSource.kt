package com.mobeedev.employees.data.datasource.local

import com.mobeedev.db.dao.EmployeeDao
import com.mobeedev.db.entity.EmployeeEntity

class EmployeeLocalDataSource(
    private val employeeDao: EmployeeDao
) {
    fun saveEmployee(employeeEntity: EmployeeEntity): EmployeeEntity =
        employeeDao.insertOrReplace(employeeEntity)

    fun editEmployee(employeeEntity: EmployeeEntity): EmployeeEntity =
        employeeDao.insertOrReplace(employeeEntity)

    fun removeEmployee(employeeId: Long) = employeeDao.removeById(employeeId)

    fun getAllEmployees() = employeeDao.selectAll()

    fun getEmployee(employeeId: Long) = employeeDao.selectById(employeeId)

    fun searchName(search: String) = employeeDao.searchName(search)
}

