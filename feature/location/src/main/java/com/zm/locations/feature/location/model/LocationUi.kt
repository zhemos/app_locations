package com.zm.locations.feature.location.model

import com.zm.locations.core.common.Ui
import com.zm.locations.core.domain.model.Location
import com.zm.locations.core.domain.model.Photo

data class LocationUi(
    val id: Int,
    val name: String,
    val photos: List<PhotoLocationUi>,
    val isEditMode: Boolean = false,
) : Ui

data class PhotoLocationUi(
    val id: Int,
    val url: String,
    val locationId: Int,
    val isEditMode: Boolean = false,
    val isChecked: Boolean = false,
) : Ui

fun Location.toUi(): LocationUi {
    return LocationUi(
        id = id,
        name = name,
        photos = photos.map { it.toUi() },
    )
}

fun Photo.toUi(): PhotoLocationUi {
    return PhotoLocationUi(
        id = id,
        url = url,
        locationId = locationId,
    )
}

fun PhotoLocationUi.toDomain(): Photo {
    return Photo(
        id = id,
        url = url,
        locationId = locationId
    )
}