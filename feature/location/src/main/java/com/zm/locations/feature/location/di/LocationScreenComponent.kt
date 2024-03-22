package com.zm.locations.feature.location.di

import com.zm.locations.feature.location.LocationFragment
import dagger.Component

@LocationScreenScope
@Component(
    dependencies = [LocationScreenDependencies::class]
)
interface LocationScreenComponent {

    @LocationScreenScope
    @Component.Factory
    interface Factory {
        fun create(
            locationScreenDependencies: LocationScreenDependencies
        ): LocationScreenComponent
    }

    fun inject(locationFragment: LocationFragment)
}