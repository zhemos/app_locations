package com.zm.locations.di.modules

import com.zm.locations.core.common.observer.ObservableController
import com.zm.locations.feature.location.observer.LocationObservable
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ObservablesModule {

    @Singleton
    @Provides
    fun provideObservableController(): ObservableController {
        return ObservableController.Impl()
    }

    @Singleton
    @Provides
    fun provideBottomNavObservable(
        observableController: ObservableController
    ): LocationObservable {
        return LocationObservable.Impl(observableController = observableController)
    }
}