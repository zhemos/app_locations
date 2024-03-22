package com.zm.locations.feature.location

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.locations.core.designsystem.components.BaseFragment
import com.zm.locations.core.designsystem.components.Navigation
import com.zm.locations.feature.location.databinding.FragmentLocationBinding
import com.zm.locations.feature.location.di.LocationScreenComponentViewModel
import com.zm.locations.feature.location.observer.LocationObserver
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.locations.core.designsystem.components.GenericSavedStateViewModelFactory
import com.zm.locations.core.designsystem.ext.setupRecyclerView
import com.zm.locations.core.designsystem.recycler.FingerprintAdapter
import com.zm.locations.feature.location.di.LocationScreenDependenciesProvider
import com.zm.locations.feature.location.fingerprints.LocationFingerprint
import com.zm.locations.feature.location.fingerprints.PhotoFingerprint
import com.zm.locations.feature.location.model.LocationDataContainer
import com.zm.locations.feature.location.observer.LocationObservable
import kotlinx.coroutines.launch
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
    @Inject
    internal lateinit var locationFingerprint: LocationFingerprint
    @Inject
    internal lateinit var photoFingerprint: PhotoFingerprint

    private val adapter: FingerprintAdapter by lazy {
        FingerprintAdapter(listOf(locationFingerprint))
    }

    private val locationDataContainer = LocationDataContainer(
        onAddPhoto = { viewModel.addPhoto(it) },
        onChangeEditMode = { id, isEnabled ->
            viewModel.changedEditMode(id, isEnabled)
        },
        onDeletePhotos = { id, photos ->
            viewModel.deletePhotos(id, photos)
        },
        onChecked = { locationId, id, isChecked ->
            viewModel.checkedPhoto(locationId, id, isChecked)
        }
    )

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLocationBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        val provider = dependenciesProvider<LocationScreenDependenciesProvider>()
        viewModelComponent.create(
            dependenciesProvider = provider,
            locationDataContainer = locationDataContainer,
            fragment = this
        )
        super.onAttach(context)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated() = with(binding) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.locations.collect {
                    showToast("${it.size}")
                    adapter.submitList(it)
                }
            }
        }
        recycler.setupRecyclerView(
            adapter = adapter,
            layoutManager = LinearLayoutManager(requireContext()),
            listItemDecorations = listOf()
        )
    }

    override fun onResume() {
        locationObservable.registerObserver(this)
        super.onResume()
    }

    override fun onStop() {
        locationObservable.removeObserver()
        super.onStop()
    }

    override fun onAddNewLocation() {
        viewModel.insertLocation()
    }
}