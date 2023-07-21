package com.goel.riderapp.domain.repository

import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.domain.models.Route
import com.goel.riderapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DriversRepository {
    suspend fun getDriverRouteInfo(): Flow<NetworkResult<Unit, Exception>>

    val driverInfoList: Flow<List<Driver>>

    suspend fun getRoutes(): List<Route>
}
