package com.zm.locations.feature.location.fingerprints

import android.content.Context
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.ext.setupRecyclerView
import com.zm.locations.core.designsystem.recycler.BaseViewHolder
import com.zm.locations.core.designsystem.recycler.FingerprintAdapter
import com.zm.locations.core.designsystem.recycler.ItemFingerprint
import com.zm.locations.feature.location.databinding.ItemLocationBinding
import com.zm.locations.feature.location.model.LocationUi
import com.zm.locations.feature.location.model.PhotoLocationUi

class LocationViewHolder(
    context: Context,
    binding: ItemLocationBinding,
    logger: Logger,
    fingerprints: List<ItemFingerprint<*, *>>,
    onAddPhoto: (Int) -> Unit,
    onChangeEditMode: (Int, Boolean) -> Unit,
    onDeletePhotos: (Int, List<PhotoLocationUi>) -> Unit,
) : BaseViewHolder<LocationUi, ItemLocationBinding>(context, binding, logger) {

    private val adapter = FingerprintAdapter(fingerprints)

    companion object {
        private const val SPAN_COUNT = 3
    }

    init {
        binding.btnAdd.setOnClickListener {
            item?.let { onAddPhoto(it.id) }
        }
        binding.btnCancel.setOnClickListener {
            item?.let { onChangeEditMode(it.id, false) }
        }
        binding.btnDelete.setOnClickListener {
            item?.let { onDeletePhotos(it.id, it.photos) }
        }
        binding.recyclerView.setupRecyclerView(
            adapter = adapter,
            layoutManager = GridLayoutManager(context, SPAN_COUNT),
            listItemDecorations = listOf()
        )
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onBind(item: LocationUi) = with(binding) {
        super.onBind(item)
        logger.debug("bind $item")
        btnDelete.isVisible = item.isEditMode
        btnCancel.isVisible = item.isEditMode
        adapter.submitList(item.photos)
    }
}