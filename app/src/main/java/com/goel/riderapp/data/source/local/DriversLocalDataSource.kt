package com.goel.riderapp.data.source.local

import com.goel.riderapp.database.dao.DriverDao
import com.goel.riderapp.database.dao.RouteDao
import com.goel.riderapp.database.entities.DriverEntity
import com.goel.riderapp.database.entities.RouteEntity
import kotlinx.coroutines.flow.Flow

/**
 * @property driverDao The Driver DAO to access driver data from DB
 * @property routeDao The Route DAO to access route data from DB
 */
class DriversLocalDataSource(
    private val driverDao: DriverDao,
    private val routeDao: RouteDao
) {
    fun getDriverRouteInfoFlow(): Flow<List<DriverEntity>> {
        return driverDao.loadDriversFlow()
    }

    suspend fun getDriverRoutes(): List<RouteEntity> {
        return routeDao.getRoutes()
    }

    suspend fun insertDrivers(driverInfo: List<DriverEntity>) {
        driverDao.insertAll(driverInfo)
    }

    suspend fun insertRoutes(routes: List<RouteEntity>) {
        routeDao.insertAll(routes)
    }
}
