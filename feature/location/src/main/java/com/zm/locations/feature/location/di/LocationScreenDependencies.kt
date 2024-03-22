package com.zm.locations.feature.location.di

import android.content.Context
import com.zm.locations.core.cache.PreferenceManager
import com.zm.locations.core.cache.room.LocationDao
import com.zm.locations.core.common.util.Logger
import com.zm.locations.feature.location.observer.LocationObservable

interface LocationScreenDependencies {
    val context: Context
    val logger: Logger
    val locationObservable: LocationObservable
    val preferenceManager: PreferenceManager
    val locationDao: LocationDao
}