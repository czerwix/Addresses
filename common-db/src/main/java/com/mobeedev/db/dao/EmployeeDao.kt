package com.mobeedev.db.dao

import com.mobeedev.db.EmployeeDBQueries
import com.mobeedev.db.entity.EmployeeEntity
import com.mobeedev.db.entity.toEntity

class EmployeeDao(
    private val employeesDBQueries: EmployeeDBQueries,
    private val addressDao: AddressDao
) {

    fun insertOrReplace(employeeEntity: EmployeeEntity) = with(employeeEntity) {
        employeesDBQueries.insertOrReplace(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            gender = gender.toString()
        )
        val employeeId = employeesDBQueries.rowId().executeAsOne()
        addresses.forEach { addressDao.insertOrReplace(it, employeeId) }

        return@with employeeEntity.copy(
            id = employeeId,
            addresses = addressDao.selectByEmployee(employeeId)
        )
    }

    fun selectAll(): MutableList<EmployeeEntity> {
        val employeesDb = employeesDBQueries.selectAll().executeAsList()
        val employeeEntityList = mutableListOf<EmployeeEntity>()

        employeesDb.forEach {
            val addresses = addressDao.selectByEmployee(it.employeeId)
            employeeEntityList.add(it.toEntity(addresses))
        }

        return employeeEntityList
    }

    fun selectById(employeeId: Long): EmployeeEntity {
        val addresses = addressDao.selectByEmployee(employeeId)
        return employeesDBQueries.selectEmployeeById(employeeId).executeAsOne().toEntity(addresses)
    }

    fun removeById(employeeId: Long) = employeeId.let {
        addressDao.removeById(it)
        employeesDBQueries.removeById(it)
    }

}