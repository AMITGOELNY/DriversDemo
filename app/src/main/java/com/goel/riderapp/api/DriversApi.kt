package com.goel.riderapp.api

import com.goel.riderapp.api.models.response.DriversResponse
import retrofit2.Response
import retrofit2.http.GET

interface DriversApi {
    @GET("data")
    suspend fun getDriverRouteData(): Response<DriversResponse>
}
