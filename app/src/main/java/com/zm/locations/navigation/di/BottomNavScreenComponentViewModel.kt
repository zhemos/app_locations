package com.zm.locations.navigation.di

import com.zm.locations.core.designsystem.components.BaseComponentViewModel
import com.zm.locations.navigation.BottomNavFragment

interface BottomNavScreenComponentViewModel :
    BaseComponentViewModel<BottomNavScreenDependenciesProvider, BottomNavFragment> {

    class Impl : BaseComponentViewModel.Impl<BottomNavScreenDependenciesProvider, BottomNavFragment>(),
        BottomNavScreenComponentViewModel {

        private var bottomNavScreenComponent: BottomNavScreenComponent? = null

        override fun create(
            dependenciesProvider: BottomNavScreenDependenciesProvider,
            fragment: BottomNavFragment
        ) {
            bottomNavScreenComponent = DaggerBottomNavScreenComponent.factory().create(
                bottomNavScreenDependencies = dependenciesProvider.bottomNavScreenDependencies
            )
            bottomNavScreenComponent?.inject(fragment)
            super.create(dependenciesProvider, fragment)
        }

        override fun onCleared() {
            bottomNavScreenComponent = null
            super.onCleared()
        }
    }
}