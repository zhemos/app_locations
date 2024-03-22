package com.zm.locations.feature.location.observer

import com.zm.locations.core.common.observer.Observable
import com.zm.locations.core.common.observer.ObservableController

interface LocationObservable : Observable<LocationObserver> {

    fun notifyAddNewLocation()

    class Impl(
        observableController: ObservableController
    ) : Observable.Base<LocationObserver>(observableController), LocationObservable {

        override val key: String get() = LocationObservable::class.java.name
        override fun notifyAddNewLocation() {
            findObserver()?.onAddNewLocation()
        }
    }
}