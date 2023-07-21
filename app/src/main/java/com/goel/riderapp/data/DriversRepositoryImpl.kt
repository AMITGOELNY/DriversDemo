package com.goel.riderapp.data

import android.util.Log
import com.goel.riderapp.api.models.response.DriversResponse
import com.goel.riderapp.data.mappers.asDatabaseModel
import com.goel.riderapp.data.mappers.toDatabaseModel
import com.goel.riderapp.data.source.local.DriversLocalDataSource
import com.goel.riderapp.data.source.remote.DriversRemoteDataSource
import com.goel.riderapp.database.entities.toDrivers
import com.goel.riderapp.database.entities.toRoutes
import com.goel.riderapp.domain.models.Driver
import com.goel.riderapp.domain.models.Route
import com.goel.riderapp.domain.repository.DriversRepository
import com.goel.riderapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * @property localDataSource The Local DataSource used to fetch info from DB
 * @property remoteDataSource The Remote DataSource used to fetch info from API
 */
class DriversRepositoryImpl(
    private val localDataSource: DriversLocalDataSource,
    private val remoteDataSource: DriversRemoteDataSource
) : DriversRepository {

    /**
     * Fetches Driver route info from API.  Inserts Record to DB on success
     */
    override suspend fun getDriverRouteInfo(): Flow<NetworkResult<Unit, Exception>> = flow {
        emit(NetworkResult.Loading)

        val result: NetworkResult<Unit, Exception> = try {
            val response = remoteDataSource.getDriverRouteInfo()
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body() as DriversResponse
                val drivers = responseBody.drivers.asDatabaseModel()
                val routes = responseBody.routes.toDatabaseModel()

                localDataSource.insertDrivers(drivers)
                localDataSource.insertRoutes(routes)
                NetworkResult.Success(Unit)
            } else {
                error("Error Parsing Response Body")
            }
        } catch (e: Exception) {
            Log.e("DriversRepository", e.message, e)
            NetworkResult.Error(e)
        }

        emit(result)
    }

    /**
     * Returns a flow of Driver's details
     */
    override val driverInfoList: Flow<List<Driver>> =
        localDataSource.getDriverRouteInfoFlow().map { it.toDrivers() }

    /**
     * Returns a flow of Routes
     */
    override suspend fun getRoutes(): List<Route> =
        localDataSource.getDriverRoutes().toRoutes()

}

