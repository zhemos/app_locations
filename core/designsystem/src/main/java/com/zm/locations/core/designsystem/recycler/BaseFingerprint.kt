package com.zm.locations.core.designsystem.recycler

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.zm.locations.core.common.Ui
import com.zm.locations.core.common.util.Logger

abstract class BaseFingerprint<I : Ui, V : ViewBinding>(
    protected val context: Context,
    protected val containerId: Int,
    protected val logger: Logger
) : ItemFingerprint<I, V> {

    override val layoutId: Int get() = containerId
}