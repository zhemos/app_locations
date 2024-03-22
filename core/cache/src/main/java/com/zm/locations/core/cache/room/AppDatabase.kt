package com.zm.locations.core.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zm.locations.core.cache.model.LocationEntity
import com.zm.locations.core.cache.model.PhotoEntity

@Database(entities = [
    LocationEntity::class,
    PhotoEntity::class,
], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}