package com.zm.locations

import android.app.Application
import com.zm.locations.di.AppComponent
import com.zm.locations.di.DaggerAppComponent

class App : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = _appComponent ?: error("app component error")

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}