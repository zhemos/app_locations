package com.zm.locations.feature.location.fingerprints

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zm.locations.core.common.Ui
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.recycler.BaseFingerprint
import com.zm.locations.core.designsystem.recycler.BaseViewHolder
import com.zm.locations.core.designsystem.recycler.ItemFingerprint
import com.zm.locations.feature.location.databinding.ItemLocationBinding
import com.zm.locations.feature.location.model.LocationUi
import com.zm.locations.feature.location.model.PhotoLocationUi

class LocationFingerprint(
    context: Context,
    containerId: Int,
    logger: Logger,
    private val fingerprints: List<ItemFingerprint<*, *>>,
    private val onAddPhoto: (Int) -> Unit,
    private val onChangeEditMode: (Int, Boolean) -> Unit,
    private val onDeletePhotos: (Int, List<PhotoLocationUi>) -> Unit,
) : BaseFingerprint<LocationUi, ItemLocationBinding>(context, containerId, logger) {

    override fun isRelativeItem(item: Ui) = (item is LocationUi)

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<LocationUi, ItemLocationBinding> {
        val binding = ItemLocationBinding.inflate(layoutInflater, parent, false)
        return LocationViewHolder(
            context = context,
            binding = binding,
            logger = logger,
            fingerprints = fingerprints,
            onAddPhoto = onAddPhoto,
            onChangeEditMode = onChangeEditMode,
            onDeletePhotos = onDeletePhotos,
        )
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<LocationUi>() {

        override fun areItemsTheSame(
            oldItem: LocationUi,
            newItem: LocationUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocationUi,
            newItem: LocationUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}