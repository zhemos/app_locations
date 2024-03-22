package com.zm.locations.feature.location.di

import com.zm.locations.core.designsystem.components.BaseComponentViewModel
import com.zm.locations.feature.location.LocationFragment
import com.zm.locations.feature.location.model.LocationDataContainer

interface LocationScreenComponentViewModel :
    BaseComponentViewModel<LocationScreenDependenciesProvider, LocationFragment> {

    fun create(
        dependenciesProvider: LocationScreenDependenciesProvider,
        locationDataContainer: LocationDataContainer,
        fragment: LocationFragment,
    )

    class Impl : BaseComponentViewModel.Impl<LocationScreenDependenciesProvider, LocationFragment>(),
        LocationScreenComponentViewModel {

        private var locationScreenComponent: LocationScreenComponent? = null

        override fun create(
            dependenciesProvider: LocationScreenDependenciesProvider,
            locationDataContainer: LocationDataContainer,
            fragment: LocationFragment
        ) {
            locationScreenComponent = DaggerLocationScreenComponent.factory().create(
                locationScreenDependencies = dependenciesProvider.locationScreenDependencies,
                locationDataContainer = locationDataContainer,
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