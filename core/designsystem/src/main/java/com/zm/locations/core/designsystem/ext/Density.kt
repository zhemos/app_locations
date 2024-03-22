package com.zm.locations.core.designsystem.ext

import android.content.res.Resources

private const val SHIFT = 0.5f

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + SHIFT).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + SHIFT)