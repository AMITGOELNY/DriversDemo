package com.goel.riderapp.domain.usecase.impl

import com.goel.riderapp.domain.models.Route
import com.goel.riderapp.domain.repository.DriversRepository
import com.goel.riderapp.domain.usecase.GetRouteInfoUseCase

private const val ROUTE_TYPE_R = "R"
private const val ROUTE_TYPE_C = "C"
private const val ROUTE_TYPE_I = "I"

class GetRouteInfoUseCaseImpl(
    private val driversRepository: DriversRepository
) : GetRouteInfoUseCase {
    override suspend fun routeInfo(driverId: Int): List<Route> {
        val routes = driversRepository.getRoutes()
        val routeMatchOnDriverId = routes.firstOrNull { it.id == driverId }
        val routeTypeMap = routes.groupBy { it.type }

        return buildList {
            routeMatchOnDriverId?.let { add(it) }

            if (driverId % 2 == 0) {
                routeTypeMap[ROUTE_TYPE_R]?.firstOrNull()?.let { add(it) }
            }

            if (driverId % 5 == 0) {
                routeTypeMap[ROUTE_TYPE_C]?.get(1)?.let { add(it) }
            }

            if (this.isEmpty()) {
                routeTypeMap[ROUTE_TYPE_I]?.lastOrNull()?.let { add(it) }
            }
        }
    }
}
