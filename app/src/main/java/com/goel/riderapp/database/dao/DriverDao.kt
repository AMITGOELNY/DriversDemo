package com.goel.riderapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goel.riderapp.database.entities.DriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {

    @Query("SELECT * FROM drivers")
    fun loadDriversFlow(): Flow<List<DriverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(driverInfoList: List<DriverEntity>): List<Long>
}
