package com.zm.locations.feature.location.model

class LocationDataContainer(
    val onAddPhoto: (Int) -> Unit,
    val onChangeEditMode: (Int, Boolean) -> Unit,
    val onDeletePhotos: (Int, List<PhotoLocationUi>) -> Unit,
    val onChecked: (Int, Int, Boolean) -> Unit,
)