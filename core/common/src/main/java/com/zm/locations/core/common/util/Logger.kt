package com.zm.locations.core.common.util

interface Logger {

    fun debug(
        message: String,
        tag: String = DEFAULT_TAG,
    )

    fun error(
        message: String,
        tag: String = DEFAULT_TAG,
    )

    companion object {
        private const val DEFAULT_TAG = "zm1996"
    }
}

interface LoggerProvider {
    val logger: Logger
}