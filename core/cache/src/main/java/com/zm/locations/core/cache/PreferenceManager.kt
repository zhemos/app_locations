package com.zm.locations.core.cache

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore

interface PreferenceManager

class PreferenceDataStoreManager(
    context: Context
) : PreferenceManager {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = DATA_STORE_NAME)

    companion object {
        private const val DATA_STORE_NAME = "store_preferences"
    }
}