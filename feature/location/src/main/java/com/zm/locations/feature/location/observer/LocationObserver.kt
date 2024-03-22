package com.zm.locations.feature.location.observer

import com.zm.locations.core.common.observer.Observer

interface LocationObserver : Observer {

    fun onAddNewLocation()
}