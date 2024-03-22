package com.zm.locations.feature.location.di.module

import com.zm.locations.core.cache.PreferenceManager
import com.zm.locations.core.cache.room.LocationDao
import com.zm.locations.core.data.LocationDataStore
import com.zm.locations.core.data.LocationDataStoreImpl
import com.zm.locations.core.data.LocationRepositoryImpl
import com.zm.locations.core.domain.LocationRepository
import com.zm.locations.feature.location.di.LocationScreenScope
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @LocationScreenScope
    @Provides
    fun provideLocationDataStore(
        preferenceManager: PreferenceManager,
        locationDao: LocationDao,
    ): LocationDataStore {
        return LocationDataStoreImpl(
            preferenceManager = preferenceManager,
            locationDao = locationDao,
        )
    }

    @LocationScreenScope
    @Provides
    fun provideLocationRepository(
        locationDataStore: LocationDataStore
    ): LocationRepository {
        return LocationRepositoryImpl(locationDataStore = locationDataStore)
    }
}