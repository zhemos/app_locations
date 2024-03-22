package com.zm.locations.core.designsystem.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.zm.locations.core.common.Ui

interface ItemFingerprint<I : Ui, V : ViewBinding> {

    val layoutId: Int

    fun isRelativeItem(item: Ui): Boolean

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<I, V>

    fun getDiffUtil(): DiffUtil.ItemCallback<I>
}