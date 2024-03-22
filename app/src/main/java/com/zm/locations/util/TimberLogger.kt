package com.zm.locations.util

import com.zm.locations.core.common.util.Logger
import timber.log.Timber
import javax.inject.Inject

class TimberLogger @Inject constructor() : Logger {

    override fun debug(message: String, tag: String) {
        Timber.tag(tag).d(message)
    }

    override fun error(message: String, tag: String) {
        Timber.tag(tag).e(message)
    }
}