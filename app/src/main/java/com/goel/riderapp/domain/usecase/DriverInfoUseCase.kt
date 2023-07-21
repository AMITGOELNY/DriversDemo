package com.goel.riderapp.domain.usecase

import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DriverInfoUseCase {
    suspend fun getDriverInfoFlow(forceReload: Boolean = false): Flow<List<Driver>>

    suspend fun getDriverRouteInfo(): Flow<NetworkResult<Unit, Exception>>
}
