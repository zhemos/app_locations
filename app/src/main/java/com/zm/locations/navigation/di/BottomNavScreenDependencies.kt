package com.zm.locations.navigation.di

import com.zm.locations.core.common.util.Logger
import com.zm.locations.feature.location.observer.LocationObservable

interface BottomNavScreenDependencies {
    val logger: Logger
    val locationObservable: LocationObservable
}