package com.goel.riderapp.di

import com.goel.riderapp.database.DriverDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { DriverDatabase.create(context = get()) }

    single { provideDriverDao(get()) }

    single { provideRouteDao(get()) }
}

private fun provideDriverDao(database: DriverDatabase) = database.driverDao
private fun provideRouteDao(database: DriverDatabase) = database.routeDao
