package com.zm.locations.feature.location.di

import com.zm.locations.feature.location.LocationFragment
import com.zm.locations.feature.location.di.module.LocationModule
import com.zm.locations.feature.location.di.module.LocationModuleUi
import com.zm.locations.feature.location.model.LocationDataContainer
import dagger.BindsInstance
import dagger.Component

@LocationScreenScope
@Component(
    modules = [LocationModule::class, LocationModuleUi::class],
    dependencies = [LocationScreenDependencies::class]
)
interface LocationScreenComponent {

    @LocationScreenScope
    @Component.Factory
    interface Factory {
        fun create(
            locationScreenDependencies: LocationScreenDependencies,
            @BindsInstance locationDataContainer: LocationDataContainer,
        ): LocationScreenComponent
    }

    fun inject(locationFragment: LocationFragment)
}