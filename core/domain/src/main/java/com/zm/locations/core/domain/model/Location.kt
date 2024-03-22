package com.zm.locations.core.domain.model

data class Location(
    val id: Int = 0,
    val name: String,
    val photos: List<Photo> = emptyList(),
)