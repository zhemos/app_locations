package com.zm.locations.di

import android.content.Context
import com.zm.locations.AppActivity
import com.zm.locations.di.modules.AppModule
import com.zm.locations.di.modules.ObservablesModule
import com.zm.locations.feature.location.di.LocationScreenDependencies
import com.zm.locations.navigation.di.BottomNavScreenDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ObservablesModule::class,
])
interface AppComponent :
    BottomNavScreenDependencies,
    LocationScreenDependencies
{

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(appActivity: AppActivity)
}