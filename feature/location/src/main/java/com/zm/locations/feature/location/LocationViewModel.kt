package com.zm.locations.feature.location

import androidx.lifecycle.SavedStateHandle
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.components.BaseViewModel
import com.zm.locations.core.designsystem.components.ViewModelAssistedFactory
import javax.inject.Inject

class LocationViewModel(
    savedStateHandle: SavedStateHandle,
    logger: Logger,
) : BaseViewModel(savedStateHandle, logger) {
}

class Factory @Inject constructor(
    private val logger: Logger
) : ViewModelAssistedFactory<LocationViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): LocationViewModel {
        return LocationViewModel(
            savedStateHandle = savedStateHandle,
            logger = logger
        )
    }
}