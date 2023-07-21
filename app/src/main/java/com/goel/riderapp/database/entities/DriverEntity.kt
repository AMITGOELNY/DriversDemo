package com.goel.riderapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goel.riderapp.domain.models.Driver

@Entity(tableName = "drivers")
data class DriverEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
)

fun List<DriverEntity>.toDrivers(): List<Driver> = map {
    Driver(it.id, it.name)
}
