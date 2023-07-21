package com.goel.riderapp.domain.usecase

import com.goel.riderapp.domain.models.Route

interface GetRouteInfoUseCase {
    suspend fun routeInfo(driverId: Int): List<Route>
}
