package dev.noelsrocha.helloapp.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

object PreferencesKey {
    val USUARIO_ATUAL_ID = intPreferencesKey("usuario_atual_id")
    val LOGADO = booleanPreferencesKey("logado")
}