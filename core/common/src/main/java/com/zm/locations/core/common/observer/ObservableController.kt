package com.zm.locations.core.common.observer

interface ObservableController {

    fun registerObserver(key: String, observer: Observer)

    fun removeObserver(key: String)

    fun <T : Observer> findObserver(key: String): T?

    fun size(): Int

    class Impl : ObservableController {
        private val observers: MutableMap<String, Observer> = HashMap()

        override fun registerObserver(key: String, observer: Observer) {
            observers[key] = observer
        }

        override fun removeObserver(key: String) {
            observers.remove(key)
        }

        override fun <T : Observer> findObserver(key: String): T? {
            return try {
                observers[key] as T?
            } catch (e: Exception) {
                null
            }
        }

        override fun size(): Int {
            return observers.size
        }
    }
}