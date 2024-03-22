package com.zm.locations.feature.location.fingerprints

import android.content.Context
import androidx.core.view.isVisible
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.recycler.BaseViewHolder
import com.zm.locations.feature.location.databinding.ItemPhotoBinding
import com.zm.locations.feature.location.model.PhotoLocationUi

class PhotoViewHolder(
    context: Context,
    binding: ItemPhotoBinding,
    logger: Logger,
    onChangeEditMode: (Int, Boolean) -> Unit,
    onChecked: (Int, Int, Boolean) -> Unit,
) : BaseViewHolder<PhotoLocationUi, ItemPhotoBinding>(context, binding, logger) {

    init {
        binding.imageView.setOnLongClickListener {
            item?.let { onChangeEditMode(it.locationId, true) }
            return@setOnLongClickListener true
        }
        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            item?.let {
                onChecked(it.locationId, it.id, isChecked)
            }
        }
    }

    override fun onBind(item: PhotoLocationUi) = with(binding) {
        super.onBind(item)
        checkbox.isVisible = item.isEditMode
    }
}