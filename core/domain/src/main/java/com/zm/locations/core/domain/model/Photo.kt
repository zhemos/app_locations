package com.zm.locations.core.domain.model

data class Photo(
    val id: Int = 0,
    val url: String,
    val locationId: Int,
)