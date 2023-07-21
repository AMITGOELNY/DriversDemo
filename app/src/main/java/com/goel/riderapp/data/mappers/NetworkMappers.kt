package com.goel.riderapp.data.mappers

import com.goel.riderapp.api.models.response.DriverNetworkModel
import com.goel.riderapp.api.models.response.RouteNetworkModel
import com.goel.riderapp.database.entities.DriverEntity
import com.goel.riderapp.database.entities.RouteEntity

fun List<DriverNetworkModel>.asDatabaseModel(): List<DriverEntity> =
    map { DriverEntity(it.id, it.name) }

fun List<RouteNetworkModel>.toDatabaseModel(): List<RouteEntity> =
    map { RouteEntity(it.id, it.type, it.name) }
