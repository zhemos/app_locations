package com.zm.locations.core.designsystem.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.zm.locations.core.common.Ui
import com.zm.locations.core.common.util.Logger

abstract class BaseViewHolder<I : Ui, V : ViewBinding>(
    protected val context: Context,
    protected val binding: V,
    protected val logger: Logger
): RecyclerView.ViewHolder(binding.root) {

    protected val tag = "ViewHolder"
    var item: I? = null

    open fun onBind(item: I) {
        this.item = item
    }

    open fun onBind(item: I, payloads: List<Any>) {
        this.item = item
    }
}