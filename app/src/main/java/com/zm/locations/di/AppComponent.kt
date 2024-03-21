package com.zm.locations.di

import android.content.Context
import com.zm.locations.AppActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(appActivity: AppActivity)
}

@Module
class AppModule