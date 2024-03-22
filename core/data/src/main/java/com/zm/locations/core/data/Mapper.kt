package com.zm.locations.core.data

import com.zm.locations.core.cache.model.LocationEntity
import com.zm.locations.core.cache.model.LocationWithPhotos
import com.zm.locations.core.cache.model.PhotoEntity
import com.zm.locations.core.domain.model.Location
import com.zm.locations.core.domain.model.Photo

fun LocationEntity.toDomain(): Location {
    return Location(id = id, name = name)
}

fun LocationWithPhotos.toDomain(): Location {
    return Location(
        id = location.id,
        name = location.name,
        photos = photos.map { it.toDomain() },
    )
}

fun PhotoEntity.toDomain(): Photo {
    return Photo(id = id, url = url, locationId = locationId)
}

fun Location.toDto(): LocationEntity {
    return LocationEntity(name = name)
}

fun Photo.toDto(locationId: Int): PhotoEntity {
    return PhotoEntity(
        url = url,
        locationId = locationId,
    )
}