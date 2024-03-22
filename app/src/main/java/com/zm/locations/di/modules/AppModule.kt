package com.zm.locations.di.modules

import com.zm.locations.core.common.util.Logger
import com.zm.locations.util.TimberLogger
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindsLogger(
        timber: TimberLogger
    ): Logger
}