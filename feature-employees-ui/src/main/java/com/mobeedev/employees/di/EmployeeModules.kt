package com.mobeedev.employees.di

import com.mobeedev.commonDomain.ModuleLoader
import com.mobeedev.employees.data.datasource.local.EmployeeLocalDataSource
import com.mobeedev.employees.data.repository.EmployeeRepositoryImpl
import com.mobeedev.employees.domain.repository.EmployeeRepository
import com.mobeedev.employees.domain.usecase.*
import com.mobeedev.employees.ui.home.EmployeeState
import com.mobeedev.employees.ui.home.EmployeeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object EmployeeModules : ModuleLoader() {
    override val modules: List<Module> =
        listOf(
            dataSourceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
}

private val useCaseModule = module {
    factory { GetEmployeeUseCase(employeeRepository = get()) }
    factory { GetEmployeesUseCase(employeeRepository = get()) }
    factory { RemoveEmployeeUseCase(employeeRepository = get()) }
    factory { SaveEmployeeUseCase(employeeRepository = get()) }
    factory { UpdateEmployeeUseCase(employeeRepository = get()) }
}

private val repositoryModule = module {
    single<EmployeeRepository> { EmployeeRepositoryImpl(employeeLocalDataSource = get()) }
}

private val dataSourceModule = module {
    single { EmployeeLocalDataSource(employeeDao = get()) }
}

private val viewModelModule = module {
    viewModel { (state: EmployeeState) ->
        EmployeeViewModel(
            state,
            getEmployeesUseCase = get(),
            getEmployeeUseCase = get(),
            removeEmployeeUseCase = get(),
            saveEmployeeUseCase = get(),
            updateEmployeeUseCase = get()
        )
    }
}