package com.zm.locations.feature.location.di.module

import android.content.Context
import com.zm.locations.core.common.util.Logger
import com.zm.locations.feature.location.R
import com.zm.locations.feature.location.di.LocationScreenScope
import com.zm.locations.feature.location.fingerprints.LocationFingerprint
import com.zm.locations.feature.location.fingerprints.PhotoFingerprint
import com.zm.locations.feature.location.model.LocationDataContainer
import dagger.Module
import dagger.Provides

@Module
class LocationModuleUi {

    @LocationScreenScope
    @Provides
    fun provideLocationFingerprint(
        context: Context,
        logger: Logger,
        locationDataContainer: LocationDataContainer,
        photoFingerprint: PhotoFingerprint,
    ): LocationFingerprint {
        return LocationFingerprint(
            context = context,
            containerId = R.layout.item_location,
            logger = logger,
            fingerprints = listOf(photoFingerprint),
            onAddPhoto = locationDataContainer.onAddPhoto,
            onChangeEditMode = locationDataContainer.onChangeEditMode,
            onDeletePhotos = locationDataContainer.onDeletePhotos,
        )
    }

    @LocationScreenScope
    @Provides
    fun providePhotoFingerprint(
        context: Context,
        logger: Logger,
        locationDataContainer: LocationDataContainer,
    ): PhotoFingerprint {
        return PhotoFingerprint(
            context = context,
            containerId = R.layout.item_photo,
            logger = logger,
            onChangeEditMode = locationDataContainer.onChangeEditMode,
            onChecked = locationDataContainer.onChecked,
        )
    }
}