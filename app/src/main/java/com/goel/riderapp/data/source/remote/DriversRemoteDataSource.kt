package com.goel.riderapp.data.source.remote

import com.goel.riderapp.api.DriversApi
import com.goel.riderapp.api.models.response.DriversResponse
import retrofit2.Response

/**
 * Fetches driver info from the [com.goel.riderapp.api.DriversApi]
 */
class DriversRemoteDataSource(private val driversApi: DriversApi) {
    suspend fun getDriverRouteInfo(): Response<DriversResponse> {
        return driversApi.getDriverRouteData()
    }
}
