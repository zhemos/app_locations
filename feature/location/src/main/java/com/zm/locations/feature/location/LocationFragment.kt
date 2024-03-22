package com.zm.locations.feature.location

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.feature.location.databinding.FragmentLocationBinding
import com.zm.locations.feature.location.di.LocationScreenComponentViewModel
import com.zm.locations.feature.location.observer.LocationObserver
import androidx.fragment.app.viewModels
import com.zm.locations.core.designsystem.components.GenericSavedStateViewModelFactory
import com.zm.locations.feature.location.di.LocationScreenDependenciesProvider
import com.zm.locations.feature.location.observer.LocationObservable
import javax.inject.Inject

interface LocationNavigation : Navigation

class LocationFragment : BaseFragment<FragmentLocationBinding, LocationNavigation>(),
    LocationObserver {

    private val viewModelComponent: LocationScreenComponentViewModel.Impl by viewModels()

    @Inject
    internal lateinit var factory: Factory
    private val viewModel: LocationViewModel by viewModels {
        GenericSavedStateViewModelFactory(factory, this)
    }

    @Inject
    internal lateinit var locationObservable: LocationObservable

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLocationBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        val provider = dependenciesProvider<LocationScreenDependenciesProvider>()
        viewModelComponent.create(dependenciesProvider = provider, fragment = this)
        super.onAttach(context)
    }

    override fun onViewCreated() = Unit

    override fun onResume() {
        locationObservable.registerObserver(this)
        super.onResume()
    }

    override fun onStop() {
        locationObservable.removeObserver()
        super.onStop()
    }

    override fun onAddNewLocation() {
        showToast("add new")
    }
}