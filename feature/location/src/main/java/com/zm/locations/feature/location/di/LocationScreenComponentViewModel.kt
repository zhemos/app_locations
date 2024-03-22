package com.zm.locations.feature.location.di

import com.zm.locations.core.designsystem.components.BaseComponentViewModel
import com.zm.locations.feature.location.LocationFragment

interface LocationScreenComponentViewModel :
    BaseComponentViewModel<LocationScreenDependenciesProvider, LocationFragment> {

    class Impl : BaseComponentViewModel.Impl<LocationScreenDependenciesProvider, LocationFragment>(),
        LocationScreenComponentViewModel {

        private var locationScreenComponent: LocationScreenComponent? = null

        override fun create(
            dependenciesProvider: LocationScreenDependenciesProvider,
            fragment: LocationFragment
        ) {
            locationScreenComponent = DaggerLocationScreenComponent.factory().create(
                locationScreenDependencies = dependenciesProvider.locationScreenDependencies
            )
            locationScreenComponent?.inject(fragment)
            super.create(dependenciesProvider, fragment)
        }

        override fun onCleared() {
            locationScreenComponent = null
            super.onCleared()
        }
    }
}