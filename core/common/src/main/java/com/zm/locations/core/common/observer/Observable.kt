package com.zm.locations.core.common.observer

interface Observable<T : Observer> {

    fun registerObserver(observer: T)

    fun removeObserver()

    fun findObserver(): T?

    abstract class Base<T : Observer>(
        private val observableController: ObservableController
    ) : Observable<T> {

        abstract val key: String

        override fun registerObserver(observer: T) {
            observableController.registerObserver(key, observer)
        }

        override fun removeObserver() {
            observableController.removeObserver(key)
        }

        override fun findObserver(): T? {
            return observableController.findObserver(key)
        }
    }
}