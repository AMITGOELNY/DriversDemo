package com.goel.riderapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goel.riderapp.database.entities.RouteEntity

@Dao
interface RouteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routes: List<RouteEntity>)

    @Query("SELECT * FROM route")
    suspend fun getRoutes(): List<RouteEntity>
}
