package com.zm.locations.feature.location.di

import com.zm.locations.core.common.util.Logger
import com.zm.locations.feature.location.observer.LocationObservable

interface LocationScreenDependencies {
    val logger: Logger
    val locationObservable: LocationObservable
}