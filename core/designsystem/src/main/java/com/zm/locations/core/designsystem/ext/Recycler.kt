package com.zm.locations.core.designsystem.ext

import androidx.recyclerview.widget.RecyclerView
import com.zm.locations.core.designsystem.recycler.FingerprintAdapter

fun RecyclerView.setupRecyclerView(
    adapter: FingerprintAdapter?,
    layoutManager: RecyclerView.LayoutManager,
    listItemDecorations: List<RecyclerView.ItemDecoration>
) {
    apply {
        if (layoutManager.isAttachedToWindow) return@apply
        listItemDecorations.forEach {
            removeItemDecoration(it)
        }
        this.adapter = adapter
        this.layoutManager = layoutManager
        listItemDecorations.forEach {
            addItemDecoration(it)
        }
    }
}