package com.zm.locations.feature.location.fingerprints

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zm.locations.core.common.Ui
import com.zm.locations.core.common.util.Logger
import com.zm.locations.core.designsystem.recycler.BaseFingerprint
import com.zm.locations.core.designsystem.recycler.BaseViewHolder
import com.zm.locations.feature.location.databinding.ItemPhotoBinding
import com.zm.locations.feature.location.model.PhotoLocationUi

class PhotoFingerprint(
    context: Context,
    containerId: Int,
    logger: Logger,
    private val onChangeEditMode: (Int, Boolean) -> Unit,
    private val onChecked: (Int, Int, Boolean) -> Unit,
): BaseFingerprint<PhotoLocationUi, ItemPhotoBinding>(context, containerId, logger) {

    override fun isRelativeItem(item: Ui) = (item is PhotoLocationUi)

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<PhotoLocationUi, ItemPhotoBinding> {
        val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(
            context = context,
            binding = binding,
            logger = logger,
            onChangeEditMode = onChangeEditMode,
            onChecked = onChecked,
        )
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<PhotoLocationUi>() {

        override fun areItemsTheSame(
            oldItem: PhotoLocationUi,
            newItem: PhotoLocationUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoLocationUi,
            newItem: PhotoLocationUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}