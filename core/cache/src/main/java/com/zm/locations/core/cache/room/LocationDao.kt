package com.zm.locations.core.cache.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.zm.locations.core.cache.model.LocationEntity
import com.zm.locations.core.cache.model.LocationWithPhotos
import com.zm.locations.core.cache.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationentity")
    fun getAll(): Flow<List<LocationEntity>>

    @Transaction
    @Query("SELECT * FROM locationentity")
    fun getLocations(): Flow<List<LocationWithPhotos>>

    @Insert
    suspend fun insert(locationEntity: LocationEntity)

    @Insert
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Delete
    suspend fun deletePhotos(photos: List<PhotoEntity>)
}