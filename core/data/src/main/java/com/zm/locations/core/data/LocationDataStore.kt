package com.zm.locations.core.data

import com.zm.locations.core.cache.PreferenceManager
import com.zm.locations.core.cache.model.LocationEntity
import com.zm.locations.core.cache.model.LocationWithPhotos
import com.zm.locations.core.cache.model.PhotoEntity
import com.zm.locations.core.cache.room.LocationDao
import kotlinx.coroutines.flow.Flow

interface LocationDataStore {

    val locations: Flow<List<LocationWithPhotos>>

    suspend fun insert(location: LocationEntity)

    suspend fun addPhotos(photos: List<PhotoEntity>)

    suspend fun deletePhotos(photos: List<PhotoEntity>)
}

class LocationDataStoreImpl(
    private val preferenceManager: PreferenceManager,
    private val locationDao: LocationDao,
) : LocationDataStore {

    override val locations: Flow<List<LocationWithPhotos>> get() = locationDao.getLocations()

    override suspend fun insert(location: LocationEntity) {
        locationDao.insert(location)
    }

    override suspend fun addPhotos(photos: List<PhotoEntity>) {
        locationDao.insertPhotos(photos)
    }

    override suspend fun deletePhotos(photos: List<PhotoEntity>) {
        locationDao.deletePhotos(photos)
    }
}
