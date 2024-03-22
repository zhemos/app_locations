package com.zm.locations.core.designsystem.components

import android.os.Bundle

interface Navigation

interface Navigator {

    fun navigate(actionId: Int)

    fun navigateWithData(actionId: Int, data: Bundle)

    fun navigateBack()
}

interface NavigatorProvider {
    val navigator: Navigator
}