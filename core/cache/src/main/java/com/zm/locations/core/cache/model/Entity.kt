package com.zm.locations.core.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
)

@Entity
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val locationId: Int,
)

class LocationWithPhotos(
    @Embedded val location: LocationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "locationId"
    )
    val photos: List<PhotoEntity>,
)