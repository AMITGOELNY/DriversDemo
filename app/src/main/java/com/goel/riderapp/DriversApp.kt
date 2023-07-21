package com.goel.riderapp

import android.app.Application
import com.goel.riderapp.di.databaseModule
import com.goel.riderapp.di.driversModule
import com.goel.riderapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DriversApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = listOf(networkModule, databaseModule, driversModule)
        startKoin {
            androidContext(this@DriversApp)
            modules(modules)
        }
    }
}
