package com.zm.locations.core.data

import com.zm.locations.core.domain.LocationRepository
import com.zm.locations.core.domain.model.Location
import com.zm.locations.core.domain.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val locationDataStore: LocationDataStore,
) : LocationRepository {

    override val locations: Flow<List<Location>> get() = locationDataStore.locations.map {
        locations -> locations.map { it.toDomain() }
    }

    override suspend fun insertLocation(location: Location) {
        locationDataStore.insert(location.toDto())
    }

    override suspend fun addPhotos(locationId: Int, photos: List<Photo>) {
        locationDataStore.addPhotos(photos.map { it.toDto(locationId) })
    }

    override suspend fun deletePhotos(locationId: Int, photos: List<Photo>) {
        locationDataStore.deletePhotos(photos.map { it.toDto(locationId) })
    }
}