package com.zm.locations.di.modules

import android.content.Context
import androidx.room.Room
import com.zm.locations.core.cache.PreferenceDataStoreManager
import com.zm.locations.core.cache.PreferenceManager
import com.zm.locations.core.cache.room.AppDatabase
import com.zm.locations.core.cache.room.LocationDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun providePreferenceManager(
        context: Context
    ): PreferenceManager {
        return PreferenceDataStoreManager(context = context)
    }

    @Singleton
    @Provides
    fun provideAppDataBase(
        context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-locations"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }
}