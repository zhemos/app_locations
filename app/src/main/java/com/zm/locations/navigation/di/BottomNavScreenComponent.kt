package com.zm.locations.navigation.di

import com.zm.locations.navigation.BottomNavFragment
import dagger.Component

@BottomNavScreenScope
@Component(
    dependencies = [BottomNavScreenDependencies::class]
)
interface BottomNavScreenComponent {

    @BottomNavScreenScope
    @Component.Factory
    interface Factory {
        fun create(
            bottomNavScreenDependencies: BottomNavScreenDependencies
        ): BottomNavScreenComponent
    }

    fun inject(bottomNavFragment: BottomNavFragment)
}