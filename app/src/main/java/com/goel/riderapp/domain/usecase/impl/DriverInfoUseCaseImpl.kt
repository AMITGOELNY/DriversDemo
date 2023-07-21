package com.goel.riderapp.domain.usecase.impl

import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.domain.repository.DriversRepository
import com.goel.riderapp.domain.usecase.DriverInfoUseCase
import com.goel.riderapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

class DriverInfoUseCaseImpl(
    private val driversRepository: DriversRepository
) : DriverInfoUseCase {

    override suspend fun getDriverRouteInfo(): Flow<NetworkResult<Unit, Exception>> =
        driversRepository.getDriverRouteInfo()

    override suspend fun getDriverInfoFlow(
        forceReload: Boolean
    ): Flow<List<Driver>> =
        driversRepository.driverInfoList
}