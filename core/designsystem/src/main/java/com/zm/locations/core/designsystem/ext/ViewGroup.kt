package com.zm.locations.core.designsystem.ext

import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach

fun ViewGroup.disableTooltip() {
    val content: View = getChildAt(0)
    if (content is ViewGroup) {
        content.forEach {
            it.setOnLongClickListener {
                return@setOnLongClickListener true
            }
            it.isHapticFeedbackEnabled = false
        }
    }
}