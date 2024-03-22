package com.zm.locations.feature.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.components.BaseViewModel
import com.zm.locations.core.designsystem.components.ViewModelAssistedFactory
import com.zm.locations.core.domain.LocationRepository
import com.zm.locations.core.domain.model.Location
import com.zm.locations.core.domain.model.Photo
import com.zm.locations.feature.location.model.LocationUi
import com.zm.locations.feature.location.model.PhotoLocationUi
import com.zm.locations.feature.location.model.toDomain
import com.zm.locations.feature.location.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel(
    savedStateHandle: SavedStateHandle,
    logger: Logger,
    private val locationRepository: LocationRepository,
) : BaseViewModel(savedStateHandle, logger) {

    private val _locations: MutableStateFlow<List<LocationUi>> = MutableStateFlow(emptyList())
    val locations: StateFlow<List<LocationUi>> = _locations

    init {
        locationRepository.locations
            .onEach { locations ->
                _locations.value = locations.map { it.toUi() }
            }
            .launchIn(viewModelScope)
    }

    fun insertLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            val location = Location(name = DEFAULT_NAME_BY_LOCATION)
            locationRepository.insertLocation(location)
        }
    }

    fun addPhoto(locationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val testPhotos = listOf(Photo(url = DEFAULT_URL, locationId = locationId))
            locationRepository.addPhotos(locationId, testPhotos)
        }
    }

    fun changedEditMode(locationId: Int, isEnabled: Boolean) {
        val index = _locations.value.indexOfFirst { it.id == locationId }
        val newList = _locations.value.toMutableList()
        val newPhotos = newList[index].photos.map {
            it.copy(isEditMode = isEnabled)
        }
        val newLocation = newList[index].copy(isEditMode = isEnabled, photos = newPhotos)
        newList.removeAt(index)
        newList.add(index, newLocation)
        _locations.value = newList
    }

    fun deletePhotos(locationId: Int, photos: List<PhotoLocationUi>) {
        viewModelScope.launch(Dispatchers.IO) {
            val checkedPhotos = photos
                .filter { it.isChecked }
                .map { it.toDomain() }
            logger.debug(checkedPhotos.toString())
            locationRepository.deletePhotos(locationId, checkedPhotos)
        }
    }

    fun checkedPhoto(locationId: Int, idPhoto: Int, isChecked: Boolean) {
//        val index = _locations.value.indexOfFirst { it.id == locationId }
//        val newList = _locations.value.toMutableList()
//        val newPhotos = newList[index].photos.toMutableList()
//        val indexPhoto = newPhotos.indexOfFirst { it.id == idPhoto }
//        val newPhoto = newPhotos[indexPhoto].copy(isChecked = isChecked)
//        newPhotos.removeAt(indexPhoto)
//        newPhotos.add(indexPhoto, newPhoto)
//        val newLocation = newList[index].copy(photos = newPhotos)
//        newList.removeAt(index)
//        newList.add(index, newLocation)
//        _locations.value = newList
    }

    companion object {
        private const val DEFAULT_NAME_BY_LOCATION = "New location"
        private const val DEFAULT_URL = "url"
    }
}

class Factory @Inject constructor(
    private val logger: Logger,
    private val locationRepository: LocationRepository,
) : ViewModelAssistedFactory<LocationViewModel> {

    override fun create(savedStateHandle: SavedStateHandle): LocationViewModel {
        return LocationViewModel(
            savedStateHandle = savedStateHandle,
            logger = logger,
            locationRepository = locationRepository,
        )
    }
}