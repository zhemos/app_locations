package com.zm.locations.core.domain

import com.zm.locations.core.domain.model.Location
import com.zm.locations.core.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    val locations: Flow<List<Location>>

    suspend fun insertLocation(location: Location)

    suspend fun addPhotos(locationId: Int, photos: List<Photo>)

    suspend fun deletePhotos(locationId: Int, photos: List<Photo>)
}