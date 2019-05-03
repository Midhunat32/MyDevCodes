package com.wisilica.androdev.ui.activity.observer_pattern

/**
 * MyObservable is the custom observable(subject)
 */
interface MyObservable {
    fun registerObserver(repositoryObserver:RepositoryObserver)
    fun removeObserver(repositoryObserver:RepositoryObserver)
    fun notifyObservers()
}