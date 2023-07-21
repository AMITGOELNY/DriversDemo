package com.goel.riderapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goel.riderapp.database.dao.DriverDao
import com.goel.riderapp.database.dao.RouteDao
import com.goel.riderapp.database.entities.DriverEntity
import com.goel.riderapp.database.entities.RouteEntity

@Database(entities = [DriverEntity::class, RouteEntity::class], version = 1)
abstract class DriverDatabase : RoomDatabase() {
    abstract val driverDao: DriverDao
    abstract val routeDao: RouteDao

    companion object {
        fun create(context: Context): DriverDatabase {
            return Room.databaseBuilder(
                context = context.applicationContext,
                klass = DriverDatabase::class.java,
                name = "driver_db"
            ).build()
        }
    }
}
