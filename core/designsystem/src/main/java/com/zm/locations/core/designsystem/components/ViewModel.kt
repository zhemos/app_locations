package com.zm.locations.core.designsystem.components

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.zm.locations.core.common.util.Logger

interface BaseComponentViewModel<Provider, Fragment> {

    fun create(
        dependenciesProvider: Provider,
        fragment: Fragment,
    )
    abstract class Impl<Provider, Fragment> : ViewModel(),
        BaseComponentViewModel<Provider, Fragment> {

        protected var dependenciesProvider: Provider? = null

        override fun create(dependenciesProvider: Provider, fragment: Fragment) {
            this.dependenciesProvider = dependenciesProvider
        }

        override fun onCleared() {
            dependenciesProvider = null
            super.onCleared()
        }
    }
}

abstract class BaseViewModel(
    protected val savedStateHandle: SavedStateHandle,
    protected val logger: Logger,
) : ViewModel()

interface ViewModelAssistedFactory<VM : ViewModel> : ViewModelProvider.Factory {
    fun create(savedStateHandle: SavedStateHandle): VM
}

class GenericSavedStateViewModelFactory<out VM : ViewModel>(
    private val viewModelFactory: ViewModelAssistedFactory<VM>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
): AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        @Suppress("UNCHECKED_CAST")
        return viewModelFactory.create(handle) as T
    }
}