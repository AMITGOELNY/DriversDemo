package com.goel.riderapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goel.riderapp.domain.models.Route

@Entity(tableName = "route")
data class RouteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "name") val name: String
)

fun List<RouteEntity>.toRoutes(): List<Route> = map {
    Route(it.id, it.type, it.name)
}
