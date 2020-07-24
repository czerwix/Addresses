package com.mobeedev.db

import com.mobeedev.commonDomain.ModuleLoader
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseModules  : ModuleLoader() {
    override val modules: List<Module> = listOf(daoModule)
}

private val daoModule = module(override = true) {

}