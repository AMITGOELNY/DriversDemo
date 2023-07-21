package com.goel.riderapp.di

import com.goel.riderapp.api.DriversApi
import com.goel.riderapp.data.DriversRepositoryImpl
import com.goel.riderapp.data.source.local.DriversLocalDataSource
import com.goel.riderapp.data.source.remote.DriversRemoteDataSource
import com.goel.riderapp.domain.repository.DriversRepository
import com.goel.riderapp.domain.usecase.DriverInfoUseCase
import com.goel.riderapp.domain.usecase.GetRouteInfoUseCase
import com.goel.riderapp.domain.usecase.impl.DriverInfoUseCaseImpl
import com.goel.riderapp.domain.usecase.impl.GetRouteInfoUseCaseImpl
import com.goel.riderapp.ui.driver.viewModel.DriversScreenViewModel
import com.goel.riderapp.ui.route.viewmodel.RouteInfoScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val driversModule = module {
    single { provideDriversApi(retrofit = get()) }

    viewModel { DriversScreenViewModel(driverInfoUseCase = get()) }

    viewModel { parameters ->
        RouteInfoScreenViewModel(id = parameters[0], getRouteInfoUseCase = get())
    }

    single<DriversRepository> {
        DriversRepositoryImpl(localDataSource = get(), remoteDataSource = get())
    }

    single<DriverInfoUseCase> { DriverInfoUseCaseImpl(driversRepository = get()) }

    single<GetRouteInfoUseCase> { GetRouteInfoUseCaseImpl(driversRepository = get()) }

    single { DriversLocalDataSource(driverDao = get(), routeDao = get()) }

    single { DriversRemoteDataSource(driversApi = get()) }
}

private fun provideDriversApi(retrofit: Retrofit): DriversApi = retrofit.createRetrofit()

private inline fun <reified T> Retrofit.createRetrofit(): T = this.create(T::class.java)
