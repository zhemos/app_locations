package com.zm.locations

import android.app.Application
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.common.util.LoggerProvider
import com.zm.locations.di.AppComponent
import com.zm.locations.di.DaggerAppComponent
import com.zm.locations.feature.location.di.LocationScreenDependencies
import com.zm.locations.feature.location.di.LocationScreenDependenciesProvider
import com.zm.locations.navigation.di.BottomNavScreenDependencies
import com.zm.locations.navigation.di.BottomNavScreenDependenciesProvider
import timber.log.Timber

class App : Application(), LoggerProvider,
    BottomNavScreenDependenciesProvider,
    LocationScreenDependenciesProvider {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = _appComponent ?: error("app component error")

    override val logger: Logger get() = appComponent.logger

    override val bottomNavScreenDependencies: BottomNavScreenDependencies
        get() = appComponent

    override val locationScreenDependencies: LocationScreenDependencies
        get() = appComponent

    override fun onCreate() {
        super.onCreate()
        initTimber()
        _appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG.not()) return
        Timber.plant(Timber.DebugTree())
    }
}